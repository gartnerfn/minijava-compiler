package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoVarEncadenada extends NodoEncadenado{
    public NodoVarEncadenada(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public Type check(){
        return null;
    }

    public Type checkAssignable(Type baseType){
        return null;
    }
}
