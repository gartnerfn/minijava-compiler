package semanticAnalyzerI.types;

import javax.xml.crypto.dsig.Reference;

public class NullType extends Type{
    public NullType(){
        super("null");
    }

    public NullType(int lineNumber){
        super("null", lineNumber);
    }

    public boolean isCompatible(Type type){
        return false;
    }

    public boolean conformsTo(Type type){
        return (type instanceof ReferenceType);
    }
}
