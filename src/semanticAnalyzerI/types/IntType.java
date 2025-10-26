package semanticAnalyzerI.types;

public class IntType extends Type {
    public IntType(){
        super("int");
    }

    public IntType(int lineNumber){
        super("int", lineNumber);
    }

    public boolean isCompatible(Type type){
        return (type instanceof IntType);
    }

    public boolean conformsTo(Type type){
        return (type instanceof IntType);
    }
}
