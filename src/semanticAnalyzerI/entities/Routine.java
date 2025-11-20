package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerII.nodes.sent.NodoBloque;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Routine {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public boolean isPublic;

    public Entity declaredIn;

    public String name;
    public int lineNumber;

    public Map<String, Parameter> parameters = new LinkedHashMap<>();
    public List<Parameter> parameterList = new ArrayList<>();
    public NodoBloque block;

    public int offset;

    public int INITIAL_PARAMETER_OFFSET;
    public int parameterCount;

    public int localVarOffset;

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
        for(Parameter parameter : parameters.values()){
            parameter.isWellDeclared();

            int offset = INITIAL_PARAMETER_OFFSET + parameters.size() - parameterCount;
            parameterCount++;
            parameter.offset = offset;

            System.out.println("Parameter " + parameter.name + " offset: " + parameter.offset);
        }
    }

    public void check(){
        symbolTable.currentRoutine = this;

        block.check();
    }

    public void generate(){
        block.generate();
    }
}
