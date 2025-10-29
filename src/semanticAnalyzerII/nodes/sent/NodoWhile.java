package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoWhile extends NodoSentencia{
    NodoExp cond;
    NodoSentencia body;

    public NodoWhile(Token tkn, NodoExp cond, NodoSentencia body){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.cond = cond;
        this.body = body;
    }

    public void check(){
        if(!cond.check().isCompatible(new BooleanType()))
            throw new SemanticException("La condición del while no es de tipo booleano", this.name, this.lineNumber);

        body.check();
    }

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public boolean guaranteeReturn(){
        return body.guaranteeReturn();
    }
}
