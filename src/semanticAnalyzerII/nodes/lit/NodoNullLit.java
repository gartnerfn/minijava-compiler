package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.NullType;
import src.Token;

public class NodoNullLit extends NodoLit{
    public NodoNullLit(Token tkn){
        super(tkn, new NullType());
    }

    public void generate(){
        symbolTable.addInstruction("PUSH 0");
    }
}

