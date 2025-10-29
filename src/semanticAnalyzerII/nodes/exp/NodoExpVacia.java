package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;

public class NodoExpVacia extends NodoExpComp{

    public NodoExpVacia(){}

    public Type check(){
        return new VoidType();
    }
}
