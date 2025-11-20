package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerII.nodes.exp.NodoOperando;


public abstract class NodoReferencia extends NodoOperando {
    public boolean isAssignable(){
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return false;
    }

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return false;
    }

    public void generate(){

    }
}
