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
    Token operator;

    public NodoExpComp(){}

    public NodoExpComp(NodoExpComp leftSide, Token operator,  NodoExpBasica rightSide){
        this.leftSide = leftSide;
        this.operator = operator;
        this.rightSide = rightSide;
    }

    public Type check() {
        Type leftType = leftSide.check();
        Type rightType = rightSide.check();

        Type expectedTypes = getExpectedTypes();
        Type resultType = getResultType(operator);

        if (!leftType.conformsTo(rightType) || !rightType.conformsTo(expectedTypes) || !leftType.conformsTo(expectedTypes))
            throw new SemanticException("Los tipos no conforman para el operador " + operator.lexeme(), operator.lexeme(), operator.lineNumber());

        return resultType;
    }

    public boolean checkAssignable() {
        return false;
    }

    public Type getExpectedTypes() {
        switch (operator.token()) {
            case "+", "-", "*", "/", "%", ">",">=","<","<=", "==", "!=" -> {
                return new IntType();
            }
            default -> {
                return new BooleanType();
            }
        }
    }

    public Type getResultType(Token operator) {
        switch (operator.token()) {
            case "+", "-", "*", "/", "%" -> {
                return new IntType();
            }
            case "&&", "||", ">",">=","<","<=","==", "!=" -> {
                return new BooleanType();
            }
            default -> {
                return new NullType();
            }
        }
    }
}
