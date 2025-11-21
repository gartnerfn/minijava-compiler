package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.ReferenceType;
import src.Token;

public class NodoStringLit extends NodoLit {

    public NodoStringLit(Token tkn){
        super(tkn, new ReferenceType(new Token("classId", "String", tkn.lineNumber())));
    }

    public void generate(){
        String stringLabel = symbolTable.getOrCreateStringLiteralLabel(value);
        symbolTable.addInstruction("PUSH " + stringLabel);
    }
}
