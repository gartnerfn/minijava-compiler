package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.ReferenceType;
import src.Token;

public class NodoStringLit extends NodoLit {
    static int stringCount = 0;

    public NodoStringLit(Token tkn){
        super(tkn, new ReferenceType(new Token("classId", "String", tkn.lineNumber())));
    }

    public void generate(){
        //TODO cambiar por objetos String y heap y etc
        symbolTable.addInstruction(".DATA");
        symbolTable.addInstruction("l_str_" + stringCount + ":");
        symbolTable.addInstruction("DW " + this.value + ", 0");

        symbolTable.addInstruction(".CODE");
        symbolTable.addInstruction("PUSH l_str_" + stringCount);

        stringCount++;
    }
}
