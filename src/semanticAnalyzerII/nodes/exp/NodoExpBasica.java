package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.*;
import semanticAnalyzerII.nodes.ref.NodoLlamadaVar;
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

    public void generate(){
        operand.generate();
        boolean isVarCall = false;
        int offset = 0;

        if(operand instanceof NodoLlamadaVar){
            isVarCall = true;
            offset = symbolTable.currentRoutine.getOffset(operand.name);
        }

        switch (unaryOperator.lexeme()) {
            case "!":
                System.out.println("entre");
                symbolTable.addInstruction("NOT");
                break;
            case "++":
                if(isVarCall){
                    symbolTable.addInstruction("PUSH 1");
                    symbolTable.addInstruction("ADD");
                    symbolTable.addInstruction("STORE " + offset);
                    symbolTable.addInstruction("LOAD " + offset);
                } else {
                    symbolTable.addInstruction("PUSH 1");
                    symbolTable.addInstruction("ADD");
                }
                break;
            case "-":
                symbolTable.addInstruction("NEG");
                break;
            case"--":
                if(isVarCall){
                    symbolTable.addInstruction("PUSH 1");
                    symbolTable.addInstruction("SUB");
                    symbolTable.addInstruction("STORE " + offset);
                    symbolTable.addInstruction("LOAD " + offset);
                } else {
                    symbolTable.addInstruction("PUSH 1");
                    symbolTable.addInstruction("ADD");
                }
                break;
        }
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
