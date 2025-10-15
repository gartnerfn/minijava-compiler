package semanticAnalyzerI.types;

import src.Token;

public class ReferenceType extends Type{
    public ReferenceType(Token tkn){
        super(tkn.lexeme(), tkn.lineNumber());
    }
}