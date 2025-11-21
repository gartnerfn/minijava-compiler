package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import src.Token;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Entity {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isAbstract;

    public boolean isConsolidated;

    public String name;
    public int lineNumber;

    public String ancestorInheritance;
    public String ancestorImplementation;

    public Map<String, Attribute> attributes = new LinkedHashMap<>();
    public Map<String, Integer> attributeOffsets;

    public Map<String, Method> methods = new LinkedHashMap<>();
    public Map<String, Integer> methodOffsets = new LinkedHashMap<>();

    public HashMap<String, Constructor> constructors = new HashMap<>();


    public Entity(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.isConsolidated = false;
        attributeOffsets = new LinkedHashMap<>();
    }

    public void addConstructor(Constructor constructor){
        Constructor previousCons = existsConstructor(constructor.name, constructor.parameters.size());

        if(previousCons != null)
            throw new SemanticException("Duplicated constructor.", constructor.name, constructor.lineNumber);

        constructors.put(constructor.name + constructor.parameters.size(), constructor);
    }

    public Constructor existsConstructor(String constructorName, int parameterCount){
        return constructors.get(constructorName + parameterCount);
    }

    public int getCIRSize(){
        return attributes.size() + 1;
    }

    public void inheritsFrom(Token ancestor){
        ancestorInheritance = ancestor.lexeme();
    }

    public void implementsFrom(Token ancestor){
        ancestorImplementation = ancestor.lexeme();
    }

    public Attribute existsAttribute(String name){
        Attribute attr = attributes.get(name + '|' + this.name);

        if(attr == null){
            Entity ent = this;

            while (ent.hasParentInheritance() || ent.hasParentImplementation()) {
                String parentInheritance = ent.getParentInheritance();
                String parentImplementation = ent.getParentImplementation();
                String parent = parentInheritance != null ? parentInheritance : parentImplementation;

                attr = attributes.get(name + '|' + parent);

                if(attr != null )
                    return attr;

                ent = symbolTable.existsEntity(parent);
            }
        }


        return attr;
    }

    public boolean hasParentInheritance(){
        return ancestorInheritance != null;
    }

    public boolean hasParentImplementation(){
        return ancestorImplementation != null;
    }

    public String getParentInheritance(){
        return ancestorInheritance;
    }

    public String getParentImplementation(){
        return ancestorImplementation;
    }

    public Method existsMethod(String methodName, int parameterCount){
        return methods.get(methodName + parameterCount);
    }

    public void addInheritedAttribute(Attribute attribute, String ancestor){
        attributes.put(attribute.name + '|' + ancestor, attribute);
    }

    public void addAttribute(Attribute attribute){
        if(existsAttribute(attribute.name) != null)
            throw new SemanticException("Duplicated attribute.", attribute.name, attribute.lineNumber);

        attributes.put(attribute.name + '|' + this.name, attribute);
    }

    public void addMethod(Method method){
        Method previousMethod = existsMethod(method.name, method.parameters.size());

        if(previousMethod != null)
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);

        methods.put(method.name + method.parameters.size(), method);
    }



    public void check(){
        symbolTable.currentEntity = this;

        for(Attribute attribute : attributes.values()){
            if(attribute.declaredIn == this)
                attribute.check();
        }

        for(Constructor constructor : constructors.values())
            constructor.check();

        for(Method method : methods.values()){
            if(method.declaredIn == this)
                method.check();
        }
    }
}
