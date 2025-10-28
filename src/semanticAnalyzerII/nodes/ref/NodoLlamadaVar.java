package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.enc.NodoEncadenado;
import src.Token;

public class NodoLlamadaVar extends NodoReferencia{
    NodoEncadenado nextInTheChain;

    public NodoLlamadaVar(Token tkn) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public void addNextInTheChain(NodoEncadenado nextInTheChain) {
        this.nextInTheChain = nextInTheChain;
    }

    public Type check(){
        Variable variable = symbolTable.existsVar(name);

        if(variable == null)
            throw new SemanticException("Variable '" + name + "' no declarada", name, lineNumber);

        //        // Si hay encadenado, delega en él
        //        if (chain != null) {
        //            return chain.checkAssignable(type);
        //        }

        System.out.println(variable.type);

        return variable.type;
    }

    public boolean checkAssignable() {
        return true;
    }
}
