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

        if(nextInTheChain != null)
            return nextInTheChain.check(type);

        return type;
    }

    public boolean isAssignable() {
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return exp.isAssignable();
    }

    public boolean canBeStatement(){
        return exp.canBeStatement();
    }
}
