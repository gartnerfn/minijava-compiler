package semanticAnalyzerI.types;

public class NullType extends Type{
    public NullType(){
        super("null");
    }

    public NullType(int lineNumber){
        super("null", lineNumber);
    }

    public boolean isCompatible(Type type){
        return (type instanceof ReferenceType);
    }

    public boolean conformsTo(Type type){
        return (type instanceof ReferenceType);
    }
}
