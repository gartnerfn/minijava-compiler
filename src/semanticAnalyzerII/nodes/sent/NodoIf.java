package semanticAnalyzerII.nodes.sent;


import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoIf extends NodoSentencia{
    NodoExp cond;
    NodoSentencia thenBody;
    NodoSentencia elseBody;

    public NodoIf(Token tkn, NodoExp cond, NodoSentencia thenBody, NodoSentencia elseBody) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public void check() {
        if(!cond.check().isCompatible(new BooleanType()))
            throw new SemanticException("La condición del if no es de tipo booleano.", cond.value, cond.lineNumber);

        thenBody.check();
        elseBody.check();
    }
}
