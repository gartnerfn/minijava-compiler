package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.Type;

public abstract class NodoExp {
    public String value;
    public int lineNumber;
    public Type type;

    public abstract Type check();
}
