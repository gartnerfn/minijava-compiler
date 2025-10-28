package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerII.nodes.sent.NodoBloque;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Routine {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isPublic;

    public Entity declaredIn;

    public String name;
    public int lineNumber;

    public Map<String, Parameter> parameters = new LinkedHashMap<>();
    public List<Parameter> parameterList = new ArrayList<>();
    NodoBloque block;

    public void addParameter(Parameter parameter){
        if(parameters.containsKey(parameter.name))
            throw new SemanticException("Duplicated parameter.", parameter.name, parameter.lineNumber);

        parameters.put(parameter.name, parameter);
        parameterList.add(parameter);
    }

    public Parameter existsParameter(String name){
        return parameters.get(name);
    }

    public void addBlock(NodoBloque block){
        this.block = block;
    }

    public void isWellDeclared(){
        for(Parameter parameter : parameters.values())
            parameter.isWellDeclared();
    }

    public void check(){
        symbolTable.currentRoutine = this;

        block.check();
    }
}
