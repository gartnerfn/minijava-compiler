package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoOperando;
import src.Token;

public abstract class NodoLit extends NodoOperando {
    public NodoLit(Token tkn, Type type){
        this.value = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.type = type;
    }

    public Type check(){
        return this.type;
    }

    public abstract void generate();
    public boolean isOperandWithCall(){
        return false;
    }
}
