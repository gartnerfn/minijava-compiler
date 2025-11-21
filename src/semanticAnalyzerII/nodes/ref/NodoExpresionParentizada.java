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

    public boolean isOperandWithCall() {
        if(nextInTheChain != null) {
            return nextInTheChain.isOperandWithCall();
        }
        return false;
    }


    public boolean isAssignable() {
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return exp.isAssignable();
    }

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return exp.canBeStatement();
    }

    public void generate(){
        exp.generate();

        if(nextInTheChain != null)
            nextInTheChain.generate();
    }
}
