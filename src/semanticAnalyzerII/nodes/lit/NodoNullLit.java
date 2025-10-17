package semanticAnalyzerII.nodes.lit;

import src.Token;

public class NodoNullLit extends NodoLit{
    public NodoNullLit(Token tkn){
        super(tkn.lexeme());
    }
}

