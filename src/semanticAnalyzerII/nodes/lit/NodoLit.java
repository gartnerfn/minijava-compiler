package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoLit extends NodoExp {
    public NodoLit(Token tkn, Type type){
        this.value = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.type = type;
    }

    public Type check(){
        return this.type;
    }
}
