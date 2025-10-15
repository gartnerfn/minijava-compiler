package semanticAnalyzerII.nodes;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.types.Type;
import src.Token;

public abstract class NodoOperando {
    SymbolTable symbolTable = SymbolTable.getInstance();

    String name;
    int lineNumber;

    public NodoOperando(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    abstract Type check();
}
