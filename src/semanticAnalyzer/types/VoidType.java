package semanticAnalyzer.types;

import src.Token;

public class VoidType extends Type{
    public VoidType(Token tkn){
        this.name = "void";
        this.lineNumber=  tkn.lineNumber();
    }
}
