package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoSentenciaConExp extends NodoSentencia{
    NodoExp exp;

    public NodoSentenciaConExp(Token tkn, NodoExp exp) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.exp = exp;
    }

    public void check(){
        exp.check();
    }
}
