package semanticAnalyzerI.types;

import semanticAnalyzerI.exceptions.SemanticException;
import src.Token;

public class CharType extends Type {
    public CharType(){
        super("boolean", 0);
    }

    public CharType(int lineNumber){
        super("boolean", lineNumber);
    }

    public boolean isCompatible(Type type){
        if(!(type instanceof CharType))
            throw new SemanticException("Char type expected.", name, lineNumber);

        return true;
    }
}
