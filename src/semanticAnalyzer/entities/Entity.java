package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import semanticAnalyzer.types.Type;
import src.Token;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Entity {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isAbstract;

    public boolean isConsolidated;

    public String name;
    public int lineNumber;

    public String ancestorInheritance;

    Map<String, Attribute> attributes = new LinkedHashMap<>();
    HashMap<String, Method> methods = new HashMap<>();

    public Entity(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.isConsolidated = false;
    }

    public  void addConstructor(Constructor constructor){}

    public  void inheritsFrom(Token ancestor){}

    public  void implementsFrom(Token ancestor){}

    public void addAttribute(Attribute attribute){
        if(attributes.containsKey(attribute.name))
            throw new SemanticException("Duplicated attribute.", attribute.name, attribute.lineNumber);

        attributes.put(attribute.name, attribute);
    }

    public abstract void addMethod(Method method);

    public Attribute existsAttribute(String attributeName){
        return attributes.get(attributeName);
    }

    public Method existsMethod(String methodName){
        return methods.get(methodName);
    }

}
