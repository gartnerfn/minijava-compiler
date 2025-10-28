package semanticAnalyzerII.nodes.ref;

import src.Token;

public class NodoLlamadaMetodo extends NodoReferencia{
    public NodoLlamadaMetodo(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }
}
