package semanticAnalyzerI.types;

public abstract class Type {
    public String name;
    public int lineNumber;

    public Type(String name, int lineNumber){
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public abstract boolean isCompatible(Type type);
}
