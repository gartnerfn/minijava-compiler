package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoWhile extends NodoSentencia{
    NodoExp cond;
    NodoSentencia body;
    private static int labelCounter = 0;

    public NodoWhile(Token tkn, NodoExp cond, NodoSentencia body){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.cond = cond;
        this.body = body;
    }

    public void check(){
        if(!cond.check().isCompatible(new BooleanType()))
            throw new SemanticException("La condición del while no es de tipo booleano", this.name, this.lineNumber);

        body.check();
    }

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public boolean guaranteeReturn(){
        return false;
    }

    public void generate(){
        int currentLabel = labelCounter++;
        String startLabel = "WHILE_START_" + currentLabel;
        String endLabel = "WHILE_END_" + currentLabel;

        symbolTable.addInstruction(startLabel+":");

        cond.generate();

        symbolTable.addInstruction("BF "+endLabel);

        body.generate();

        symbolTable.addInstruction(endLabel+":");
    }
}
