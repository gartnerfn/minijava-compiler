package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoAsignacion extends NodoSentencia {
    NodoExp leftSide;
    NodoExp rightSide;

    public NodoAsignacion(Token tkn) {
        this.lineNumber = tkn.lineNumber();
    }

    public void check(){
        Type leftType = leftSide.check();
        Type rightType = rightSide.check();

        if (!rightType.isCompatible(leftType))
            throw new SemanticException("Tipos incompatibles en la asignación", name, lineNumber);
    }
}
