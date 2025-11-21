package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoAsignacion extends NodoExpComp {
    NodoExpComp leftSide;
    NodoExpComp rightSide;

    public NodoAsignacion(Token tkn, NodoExpComp leftSide, NodoExpComp rightSide) {
        this.value = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Type check(){
        if(!leftSide.isAssignable())
            throw new SemanticException("El lado izquierdo de la asignación no es asignable", value, lineNumber);

        Type leftType = leftSide.check();
        Type rightType = rightSide.check();

        if (!rightType.conformsTo(leftType))
            throw new SemanticException("Tipos incompatibles en la asignación, " + rightType.name + " no conforma con " + leftType.name, value, lineNumber);

        return leftType;
    }

    public boolean isAssignable(){
        return false;
    }

    public boolean canBeStatement(){
        return true;
    }

    public void generate(){
        rightSide.generate();

//        if(!isLeftSide)
//            symbolTable.addInstruction("DUP");

        leftSide.isLeftSide = true;
        leftSide.generate();
    }
}
