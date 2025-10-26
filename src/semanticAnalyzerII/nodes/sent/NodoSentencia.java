package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.SymbolTable;

public abstract class NodoSentencia {
    SymbolTable symbolTable = SymbolTable.getInstance();

    String name;
    int lineNumber;

    public abstract void check();
}
