package semanticAnalyzerI.types;

public abstract class Type {
    public String name;
    public int lineNumber;

    public Type(String name){
        this.name = name;
        this.lineNumber = -1;
    }

    public Type(String name, int lineNumber){
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public abstract boolean isCompatible(Type type);

    public abstract boolean conformsTo(Type type);
}
