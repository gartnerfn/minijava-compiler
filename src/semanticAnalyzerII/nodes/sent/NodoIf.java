package semanticAnalyzerII.nodes.sent;


import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerII.nodes.exp.NodoExp;

public class NodoIf extends NodoSentencia{
    NodoExp cond;
    NodoSentencia thenBody;
    NodoSentencia elseBody;

    public NodoIf(NodoExp cond, NodoSentencia thenBody, NodoSentencia elseBody) {
        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public void check(){
        cond.check().isCompatible(new BooleanType());
        thenBody.check();
        if(elseBody != null){
            elseBody.check();
        }
    }

}
