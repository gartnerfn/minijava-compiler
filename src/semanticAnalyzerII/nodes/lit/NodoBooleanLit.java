package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.BooleanType;
import src.Token;

public class NodoBooleanLit extends NodoLit{
    public NodoBooleanLit(Token tkn){
        super(tkn, new BooleanType());
    }
}
