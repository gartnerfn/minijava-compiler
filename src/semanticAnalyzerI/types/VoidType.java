package semanticAnalyzerI.types;

public class VoidType extends Type{
    public VoidType(){
        super("void");
    }

    public VoidType(int lineNumber){
        super("void", lineNumber);
    }

    public boolean isCompatible(Type type){
        return false;
    }

    public boolean conformsTo(Type type){
        return (type instanceof VoidType);
    }
}
