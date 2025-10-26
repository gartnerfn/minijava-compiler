package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoExpBinaria extends NodoExpComp{
    NodoExp leftSide;
    NodoExp rightSide;
    Token operator;

    public NodoExpBinaria(Token tkn){
        this.operator = tkn;
    }

    public Type check(){
        return null;
    }
}
