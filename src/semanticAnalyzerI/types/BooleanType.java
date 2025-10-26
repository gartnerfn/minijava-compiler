package semanticAnalyzerI.types;

public class BooleanType extends Type {
    public BooleanType(){
        super("boolean");
    }

    public BooleanType(int lineNumber){
        super("boolean", lineNumber);
    }

    public boolean isCompatible(Type type){
        return (type instanceof BooleanType);
    }

    public boolean conformsTo(Type type){
        return (type instanceof BooleanType);
    }
}
