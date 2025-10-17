package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.Type;

public class NodoExpVacia extends NodoExp{
    public Type check(){
        return new BooleanType();
    }
}
