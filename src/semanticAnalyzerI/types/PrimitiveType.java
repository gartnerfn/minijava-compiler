package semanticAnalyzerI.types;

import src.Token;

public class PrimitiveType extends Type{
    public PrimitiveType(Token tkn){
        super(tkn.lexeme(), tkn.lineNumber());
    }
}
