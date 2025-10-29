package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;

public class NodoExpresionParentizada extends NodoReferencia{
    NodoExp exp;

    public NodoExpresionParentizada(NodoExp exp){
        this.exp = exp;
    }

    public Type check() {
        Type type = exp.check();

//        if(optionalChaining != null){
//            return optionalChaining.check(type);
//        }

        return type;
    }
}
