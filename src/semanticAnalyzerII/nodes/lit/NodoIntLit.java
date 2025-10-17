package semanticAnalyzerII.nodes.lit;

import src.Token;

public class NodoIntLit extends NodoLit{
    public NodoIntLit(Token tkn){
        super(tkn.lexeme());
    }
}
