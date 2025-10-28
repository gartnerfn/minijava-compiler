package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.types.Type;

public abstract class NodoEncadenado {
    public String name;
    public int lineNumber;
    public NodoEncadenado nextInTheChain;

    public abstract Type check();
    public abstract Type checkAssignable(Type baseType);
}
