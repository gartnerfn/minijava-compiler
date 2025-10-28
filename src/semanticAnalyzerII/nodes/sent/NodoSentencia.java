package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.SymbolTable;

public abstract class NodoSentencia {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public String name;
    public int lineNumber;

    public abstract void check();
}
