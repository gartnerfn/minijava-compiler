package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.exceptions.SemanticException;
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
        Type operandType = operand.check();
        Type expectedType = getExpectedType();

        if (!operandType.conformsTo(expectedType))
            throw new SemanticException("El tipo " + operandType.name + " no conforma con el tipo esperado " + expectedType.name + " para el operador " + unaryOperator.lexeme(), unaryOperator.lexeme(), unaryOperator.lineNumber());

        this.type = operandType;

        return this.type;
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

    public boolean canBeStatement(){
        return unaryOperator != null && (unaryOperator.lexeme().equals("++") || unaryOperator.lexeme().equals("--"));
    }
}
