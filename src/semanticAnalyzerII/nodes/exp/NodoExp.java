package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.types.Type;

public abstract class NodoExp {
    public SymbolTable symbolTable = SymbolTable.getInstance();

    public String value;
    public String name;
    public int lineNumber;
    public Type type;

    public abstract Type check();
    public abstract boolean isAssignable();
    public boolean canBeStatement(){
        return false;
    }
    public abstract void generate();
}
