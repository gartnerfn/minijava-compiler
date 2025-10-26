package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.SymbolTable;

public abstract class NodoOperando extends NodoExpComp{
    public SymbolTable symbolTable = SymbolTable.getInstance();
}
