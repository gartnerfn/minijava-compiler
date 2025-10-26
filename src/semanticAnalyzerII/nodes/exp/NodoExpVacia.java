package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;

public class NodoExpVacia extends NodoExp{

    public NodoExpVacia(){}

    public Type check(){
        return new VoidType();
    }
}
