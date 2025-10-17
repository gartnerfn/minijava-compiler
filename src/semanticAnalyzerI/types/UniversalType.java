package semanticAnalyzerI.types;

import src.Token;

public class UniversalType extends Type{
    public UniversalType(Token tkn){
        super(tkn.lexeme(), tkn.lineNumber());
    }
    
    public boolean isCompatible(Type type){
        return true;
    }
}
