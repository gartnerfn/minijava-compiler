package semanticAnalyzer.types;

import src.Token;

public class PrimitiveType extends Type{
    public PrimitiveType(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber=  tkn.lineNumber();
    }
}
