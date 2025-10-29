package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaEncadenada extends NodoEncadenado{

    List<NodoExp> args;

    public NodoLlamadaEncadenada(Token tkn, List<NodoExp> args){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.args = args;
    }

    public Type check(){
        return null;
    }

    public Type checkAssignable(Type baseType){
        return null;
    }
}
