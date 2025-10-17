package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerII.nodes.exp.NodoExp;

public class NodoSentenciaConExp extends NodoSentencia{
    NodoExp exp;

    public void check(){
        exp.check();
    }

}
