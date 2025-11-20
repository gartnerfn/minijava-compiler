package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.SymbolTable;

public abstract class NodoSentencia {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public String name;
    public int lineNumber;

    public abstract String getName();
    public abstract int getLineNumber();

    public abstract void check();
    public abstract boolean guaranteeReturn();
    public abstract void generate();
}
