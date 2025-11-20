package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.BooleanType;
import src.Token;

public class NodoBooleanLit extends NodoLit{
    public NodoBooleanLit(Token tkn){
        super(tkn, new BooleanType());
    }

    public void generate(){
        if(this.value.equals("true"))
            symbolTable.addInstruction("PUSH 1");
        else
            symbolTable.addInstruction("PUSH 0");
    }
}
