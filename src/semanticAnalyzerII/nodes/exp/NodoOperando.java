package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerII.nodes.enc.NodoEncadenado;

public abstract class NodoOperando extends NodoExpBasica {
    public SymbolTable symbolTable = SymbolTable.getInstance();

    public NodoEncadenado nextInTheChain;

    public void setNextInTheChain(NodoEncadenado nextInTheChain) {
        this.nextInTheChain = nextInTheChain;
    }
}
