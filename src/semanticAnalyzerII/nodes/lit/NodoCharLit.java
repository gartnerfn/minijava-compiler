package semanticAnalyzerII.nodes.lit;

import src.Token;

public class NodoCharLit extends NodoLit{
    public NodoCharLit(Token tkn){
        super(tkn.lexeme());
    }
}
