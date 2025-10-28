package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.IntType;
import semanticAnalyzerI.types.NullType;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoExpComp extends NodoExp{
    NodoExpComp leftSide;
    NodoExpBasica rightSide;

    public NodoExpComp(){}

    public NodoExpComp(NodoExpComp leftSide, Token operator, NodoExpBasica rightSide){
        this.leftSide = leftSide;

        this.value = operator.lexeme();
        this.lineNumber = operator.lineNumber();

        this.rightSide = rightSide;
    }

    public Type check() {
        Type leftType = leftSide.check();
        Type rightType = rightSide.check();

        Type expectedTypes = getExpectedTypes();
        Type resultType = getResultType();

        if(value.equals("==") || value.equals("!=")){
            if(!leftType.conformsTo(rightType)) {
                throw new SemanticException("Los tipos no conforman para el operador " + value, value, lineNumber);
            }
        } else if (!leftType.isCompatible(rightType) || !leftType.conformsTo(expectedTypes))
            throw new SemanticException("Los tipos no conforman para el operador " + value, value, lineNumber);


        return resultType;
    }

    public boolean checkAssignable() {
        return false;
    }

    public Type getExpectedTypes() {
        switch (value) {
            case "+", "-", "*", "/", "%", ">",">=","<","<=" -> {
                return new IntType();
            }
            default -> {
                return new BooleanType();
            }
        }
    }

    public Type getResultType() {
        switch (value) {
            case "+", "-", "*", "/", "%" -> {
                return new IntType();
            }
            default -> {
                return new BooleanType();
            }
        }
    }
}
