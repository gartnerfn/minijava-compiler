package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Routine {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isPublic;

    String name;
    int lineNumber;

    Map<String, Parameter> parameters = new LinkedHashMap<>();

    public void addParameter(Parameter parameter){
        if(parameters.containsKey(parameter.name))
            throw new SemanticException("Duplicated parameter.", parameter.name, parameter.lineNumber);

        parameters.put(parameter.name, parameter);
    }

    void isWellDeclared(){
        for(Parameter parameter : parameters.values())
            parameter.isWellDeclared();
    }
}
