package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.SymbolTable;

public abstract class NodoOperando extends NodoExpBasica{
    public SymbolTable symbolTable = SymbolTable.getInstance();
}
