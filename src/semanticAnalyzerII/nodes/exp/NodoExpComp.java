package semanticAnalyzerII.nodes.exp;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.IntType;
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
            if(!leftType.isCompatible(rightType))
                throw new SemanticException("Los tipos no conforman para el operador " + value, value, lineNumber);

        } else if (!leftType.isCompatible(rightType) || !leftType.isCompatible(expectedTypes))
            throw new SemanticException("Los tipos no conforman para el operador " + value, value, lineNumber);


        return resultType;
    }

    public boolean isAssignable() {
        return false;
    }

    public void generate(){
        leftSide.generate();
        rightSide.generate();

        switch (value) {
            case "+":
                symbolTable.addInstruction("ADD");
                break;
            case "-":
                symbolTable.addInstruction("SUB");
                break;
            case "*":
                symbolTable.addInstruction("MUL");
                break;
            case"/":
                symbolTable.addInstruction("DIV");
                break;
            case "%":
                symbolTable.addInstruction("MOD");
                break;
            case "&&":
                symbolTable.addInstruction("AND");
                break;
            case "||":
                symbolTable.addInstruction("OR");
                break;
            case "==":
                symbolTable.addInstruction("EQ");
                break;
            case "!=":
                symbolTable.addInstruction("NE");
                break;
            case "<":
                symbolTable.addInstruction("LT");
                break;
            case ">":
                symbolTable.addInstruction("GT");
                break;
            case "<=":
                symbolTable.addInstruction("LE");
                break;
            case ">=":
                symbolTable.addInstruction("GE");
                break;
        }
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
