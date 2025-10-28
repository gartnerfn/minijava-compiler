package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import semanticAnalyzerII.nodes.exp.NodoExpComp;
import src.Token;

public class NodoAsignacion extends NodoExp {
    NodoExpComp leftSide;
    NodoExpComp rightSide;

    public NodoAsignacion(Token tkn, NodoExpComp leftSide, NodoExpComp rightSide) {
        this.value = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Type check(){
        if(!leftSide.checkAssignable())
            throw new SemanticException("El lado izquierdo de la asignación no es asignable", value, lineNumber);

        Type leftType = leftSide.check();
        Type rightType = rightSide.check();

        Variable leftVar = symbolTable.existsVar(leftSide.name);

        if(leftVar == null)
            throw new SemanticException("La variable '" + leftSide.name + "' no ha sido declarada", leftSide.name, lineNumber);

        if (!rightType.conformsTo(leftType))
            throw new SemanticException("Tipos incompatibles en la asignación, " + rightType + " no conforma con " + leftType, value, lineNumber);

        return leftType;
    }

    public boolean checkAssignable(){
        return true;
    }
}
