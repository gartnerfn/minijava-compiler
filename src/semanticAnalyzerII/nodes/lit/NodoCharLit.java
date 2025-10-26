package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.CharType;
import src.Token;

public class NodoCharLit extends NodoLit{
    public NodoCharLit(Token tkn){
        super(tkn, new CharType());
    }
}
