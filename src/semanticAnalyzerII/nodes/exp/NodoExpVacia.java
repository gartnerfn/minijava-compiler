package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;

public class NodoExpVacia extends NodoExpComp{

    public NodoExpVacia(){}

    public Type check(){
        return new VoidType();
    }
}
