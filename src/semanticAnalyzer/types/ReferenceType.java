package semanticAnalyzer.types;

import src.Token;

public class ReferenceType extends Type{
    public ReferenceType(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber=  tkn.lineNumber();
    }
}