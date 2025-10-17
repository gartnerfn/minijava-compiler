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
    public HashMap<String, Method> methods = new HashMap<>();
    public HashMap<String, Constructor> constructors = new HashMap<>();

    public Entity(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.isConsolidated = false;
    }

    public void addConstructor(Constructor constructor){
        Constructor previousCons = existsConstructor(constructor);

        if(previousCons != null)
            throw new SemanticException("Duplicated constructor.", constructor.name, constructor.lineNumber);

        constructors.put(constructor.name + constructor.parameters.size(), constructor);
    }

    public Constructor existsConstructor(Constructor constructor){
        return constructors.get(constructor.name + constructor.parameters.size());
    }

    public void inheritsFrom(Token ancestor){
        ancestorInheritance = ancestor.lexeme();
    }

    public void implementsFrom(Token ancestor){
        ancestorImplementation = ancestor.lexeme();
    }

    public boolean existsAttribute(Attribute attribute){
        return attributes.containsKey(attribute.name + '|' + this.name);
    }

    public Method existsMethod(Method method){
        return methods.get(method.name + method.parameters.size());
    }

    public void addInheritedAttribute(Attribute attribute, String ancestor){
        attributes.put(attribute.name + '|' + ancestor, attribute);
    }

    public void addAttribute(Attribute attribute){
        if(existsAttribute(attribute))
            throw new SemanticException("Duplicated attribute.", attribute.name, attribute.lineNumber);

        attributes.put(attribute.name + '|' + this.name, attribute);
    }

    public void addMethod(Method method){
        Method previousMethod = existsMethod(method);

        if(previousMethod != null)
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);

        methods.put(method.name + method.parameters.size(), method);
    }

    public void check(){
        symbolTable.currentEntity = this;

        for(Method method : methods.values()){
            method.check();
        }

        for(Constructor constructor : constructors.values())
            constructor.check();
    }
}
