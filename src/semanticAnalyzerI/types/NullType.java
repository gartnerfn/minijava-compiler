package semanticAnalyzerI.types;

public class NullType extends Type{
    public NullType(){
        super("null", 0);
    }

    public boolean isCompatible(Type type){
        return false;
    }
}
