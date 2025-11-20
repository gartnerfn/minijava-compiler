package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.IntType;
import src.Token;

public class NodoIntLit extends NodoLit{
    public NodoIntLit(Token tkn){
        super(tkn, new IntType());
    }

    public void generate(){
        symbolTable.addInstruction("PUSH " + this.value);
    }
}
