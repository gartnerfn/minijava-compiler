package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
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
    String ancestorImplementation;

    Map<String, Attribute> attributes = new LinkedHashMap<>();
    HashMap<String, Method> methods = new HashMap<>();
    HashMap<String, Constructor> constructors = new HashMap<>();

    public Entity(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.isConsolidated = false;
    }

    public void addConstructor(Constructor constructor){
        Constructor previousCons = constructors.get(constructor.name);

        if(previousCons != null && previousCons.parameters.size() == constructor.parameters.size())
            throw new SemanticException("Duplicated constructor.", constructor.name, constructor.lineNumber);

        constructors.put(constructor.name, constructor);
    }

    public void inheritsFrom(Token ancestor){
        ancestorInheritance = ancestor.lexeme();
    }

    public void implementsFrom(Token ancestor){
        ancestorImplementation = ancestor.lexeme();
    }

    public void addAttribute(Attribute attribute){
        if(attributes.containsKey(attribute.name))
            throw new SemanticException("Duplicated attribute.", attribute.name, attribute.lineNumber);

        attributes.put(attribute.name, attribute);
    }

    public void addMethod(Method method){
        Method previousMethod = methods.get(method.name);

        if(previousMethod != null && previousMethod.parameters.size() == method.parameters.size())
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);

        methods.put(method.name, method);
    }
}
