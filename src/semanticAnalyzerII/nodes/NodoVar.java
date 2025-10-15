package semanticAnalyzerII.nodes;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.enc.NodoEncadenado;
import src.Token;

public class NodoVar extends NodoOperando {
    String name;
    NodoEncadenado chain;
    Variable varInST;

    public NodoVar(Token tkn, NodoEncadenado chain) {
        super(tkn);
        this.chain = chain;
    }

    Type check(){
        if(symbolTable.currentRoutine.existsParameter(name)){
            varInST = symbolTable.currentRoutine.getParameter(name);
        }

        return null;
    }
}
