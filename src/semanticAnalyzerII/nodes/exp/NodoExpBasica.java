package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.types.*;
import src.Token;

public class NodoExpBasica extends NodoExpComp{
    NodoOperando operand;
    Token unaryOperator;

    public NodoExpBasica(){}

    public NodoExpBasica(Token unaryOperator, NodoOperando operand){
        this.unaryOperator = unaryOperator;
        this.operand = operand;
    }

    public Type check(){
        System.out.println(operand);

        Type operandType = operand.type;
        Type expectedType = getExpectedType();

        if (!operandType.conformsTo(expectedType))
            throw new semanticAnalyzerI.exceptions.SemanticException("El tipo " + operandType + " del operando " + operand.name + " no conforma con el tipo esperado " + expectedType + " para el operador " + unaryOperator.lexeme(), unaryOperator.lexeme(), unaryOperator.lineNumber());

        this.type = operandType;

        return this.type;
    }

    public boolean checkAssignable(){
        return this.type instanceof ReferenceType;
    }

    public Type getExpectedType(){
        switch (unaryOperator.token()) {
            case "+", "-", "++", "--" -> {
                return new IntType();
            }
            default -> {
                return new BooleanType();
            }
        }
    }
}
