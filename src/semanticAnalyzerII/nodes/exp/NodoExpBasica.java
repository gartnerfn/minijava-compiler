package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoExpBasica extends NodoExpComp{
    NodoOperando operand;
    Token operator;

    public NodoExpBasica(Token tkn){
        this.operator = tkn;
    }

    public void setOperand(NodoOperando operand){
        this.operand = operand;
    }

    public Type check(){
        return null;
    }
}
