package semanticAnalyzerI.types;

public class UniversalType extends Type{
    public UniversalType(){
        super("universal");
    }
    
    public boolean isCompatible(Type type){
        return true;
    }

    public boolean conformsTo(Type type){
        return true;
    }
}
