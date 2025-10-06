package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import src.Token;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Class {
    SymbolTable symbolTable = SymbolTable.getInstance();

    String name;
    int lineNumber;
    String ancestor;

    private Map<String, Attribute> attributes = new LinkedHashMap<>();
    private HashMap<String, Method> methods = new HashMap<>();
    private HashMap<String, Constructor> constructors = new HashMap<>();

    public Class(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public void inheritsFrom(Token ancestor){
        this.ancestor = ancestor.lexeme();
    }

    public void addAttribute(Attribute attribute){
        if(attributes.containsKey(attribute.name))
            throw new SemanticException("Duplicated attribute.", attribute.name, attribute.lineNumber);
    }

    public void addMethod(Method method){
        if(attributes.containsKey(method.name))
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);
    }

    public void addConstructor(Attribute constructor){
        if(attributes.containsKey(constructor.name))
            throw new SemanticException("Duplicated constructor.", constructor.name, constructor.lineNumber);
    }

    public Attribute existsAttribute(String attributeName){
        return attributes.get(attributeName);
    }

    public Method existsMethod(String methodName){
        return methods.get(methodName);
    }

    public Constructor existsConstructor(String constructorName){
        return constructors.get(constructorName);
    }

    public void isWellDeclared(){
        if(name.equals("Object")) return;
        System.out.println(ancestor);
        System.out.println("^");
        System.out.println(name + "\n");

        Class ancestorClass = symbolTable.existsClass(ancestor);

        if(ancestorClass == null)
            throw new SemanticException("La clase que se extiende no existe.", ancestor, lineNumber);

        if(ancestor.equals(name))
            throw new SemanticException("Herencia circular entre la misma clase.", ancestor, lineNumber);

        while(ancestorClass.ancestor != null ){
            if( ancestorClass.ancestor.equals(name))
                throw new SemanticException("Herencia circular.", ancestor, lineNumber);
            ancestorClass = symbolTable.existsClass(ancestorClass.ancestor);
        }


        for(Attribute attribute : attributes.values())
            attribute.isWellDeclared();

        for(Method method : methods.values())
            method.isWellDeclared();

        for(Constructor constructor : constructors.values())
            constructor.isWellDeclared();
    }
}
