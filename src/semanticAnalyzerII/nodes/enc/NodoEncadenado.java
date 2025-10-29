package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.types.Type;

public abstract class NodoEncadenado {
    public SymbolTable symbolTable = SymbolTable.getInstance();

    public String name;
    public int lineNumber;
    public NodoEncadenado nextInTheChain;

    public void setNextInTheChain(NodoEncadenado nextInTheChain) {
        this.nextInTheChain = nextInTheChain;
    }

    public abstract Type check(Type previousType);
    public abstract boolean isAssignable();
    public abstract boolean canBeStatement();
}
