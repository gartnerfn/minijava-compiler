package semanticAnalyzerI.types;

import semanticAnalyzerI.exceptions.SemanticException;

public class BooleanType extends Type {
    public BooleanType(){
        super("boolean", 0);
    }

    public BooleanType(int lineNumber){
        super("boolean", lineNumber);
    }

    public boolean isCompatible(Type type){
        if(!(type instanceof BooleanType))
            throw new SemanticException("Boolean type expected.", name, lineNumber);

        return true;
    }
}
