package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerII.nodes.sent.NodoBloque;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import semanticAnalyzerII.nodes.sent.NodoVarLocal;

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
    public Map<String, Integer> parametersOffset = new LinkedHashMap<>();
    public List<Parameter> parameterList = new ArrayList<>();

    public Map<String, NodoVarLocal> localVars = new LinkedHashMap<>();
    public Map<String, Integer> localVarsOffset = new LinkedHashMap<>();

    public NodoBloque block;

    public int INITIAL_PARAMETER_OFFSET;
    public int parameterCount;

    public void addParameter(Parameter parameter){
        if(parameters.containsKey(parameter.name))
            throw new SemanticException("Duplicated parameter.", parameter.name, parameter.lineNumber);

        parameters.put(parameter.name, parameter);
        parameterList.add(parameter);
    }

    public int getOffset(String name){
        if(parametersOffset.containsKey(name))
            return parametersOffset.get(name);
        else
            return localVarsOffset.get(name);
    }

    public int getParameterOffset(String name){
        return parametersOffset.get(name);
    }

    public Parameter existsParameter(String name){
        return parameters.get(name);
    }

    public void addLocalVar(NodoVarLocal localVar){
        localVarsOffset.put(localVar.name, -1 * (localVars.size()));
        localVars.put(localVar.name, localVar);
    }

    public int getLocalVarOffset(String name){
        return localVarsOffset.get(name);
    }

    public void addBlock(NodoBloque block){
        this.block = block;
    }

    public void isWellDeclared(){
        for(Parameter parameter : parameters.values()){
            parameter.isWellDeclared();

            int offset = INITIAL_PARAMETER_OFFSET + parameters.size() - parameterCount - 1;
            parameterCount++;

            parametersOffset.put(parameter.name, offset);

            System.out.println("Parameter " + parameter.name + " offset: " + offset);
        }
    }

    public void check(){
        symbolTable.currentRoutine = this;

        block.check();
    }

    public void generate(){
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");

        block.generate();

        if(!localVars.isEmpty())
            symbolTable.addInstruction("FMEM " + localVars.size());

        symbolTable.addInstruction("STOREFP");
    }
}
