package semanticAnalyzerI.types;

import semanticAnalyzerI.exceptions.SemanticException;
import src.Token;

public class IntType extends Type {
    public IntType(){
        super("int", 0);
    }

    public IntType(int lineNumber){
        super("int", lineNumber);
    }

    public boolean isCompatible(Type type){
        if(!(type instanceof IntType))
            throw new SemanticException("Int type expected.", name, lineNumber);

        return true;
    }
}
