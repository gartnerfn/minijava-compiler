package semanticAnalyzerI.types;

public class CharType extends Type {
    public CharType(){
        super("char");
    }

    public CharType(int lineNumber){
        super("char", lineNumber);
    }

    public boolean isCompatible(Type type){
        return (type instanceof CharType);
    }

    public boolean conformsTo(Type type){
        return (type instanceof CharType);
    }
}
