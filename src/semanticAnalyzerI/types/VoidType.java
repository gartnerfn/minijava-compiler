package semanticAnalyzerI.types;

import src.Token;

public class VoidType extends Type{
    public VoidType(Token tkn){
        super("void", tkn.lineNumber());
    }
}
