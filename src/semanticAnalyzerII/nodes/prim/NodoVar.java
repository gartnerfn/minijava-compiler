package semanticAnalyzerII.nodes.prim;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.enc.NodoEncadenado;
import semanticAnalyzerII.nodes.exp.NodoOperando;
import src.Token;

public class NodoVar extends NodoOperando {
    String name;
    int lineNumber;

    NodoEncadenado chain;
    Variable varInST;

    public NodoVar(Token tkn, NodoEncadenado chain) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.chain = chain;
    }

    public Type check(){
        if(symbolTable.currentRoutine.existsParameter(name)){
            varInST = symbolTable.currentRoutine.getParameter(name);
        }

        return null;
    }
}
