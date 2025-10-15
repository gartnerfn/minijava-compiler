package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Routine {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isPublic;

    public String name;
    public int lineNumber;

    public Map<String, Parameter> parameters = new LinkedHashMap<>();
    Block block;

    public void addParameter(Parameter parameter){
        if(parameters.containsKey(parameter.name))
            throw new SemanticException("Duplicated parameter.", parameter.name, parameter.lineNumber);

        parameters.put(parameter.name, parameter);
    }

    public boolean existsParameter(String name){
        return parameters.containsKey(name);
    }

    public Parameter getParameter(String name){
        return parameters.get(name);
    }

    public void addBlock(Block block){
        this.block = block;
    }

    void isWellDeclared(){
        for(Parameter parameter : parameters.values())
            parameter.isWellDeclared();
    }
}
