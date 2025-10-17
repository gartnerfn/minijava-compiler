package semanticAnalyzerII.nodes.lit;

import src.Token;

public class NodoBooleanLit extends NodoLit{
    public NodoBooleanLit(Token tkn){
        super(tkn.lexeme());
    }
}
