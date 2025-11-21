package semanticAnalyzerII.nodes.sent;


import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.BooleanType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoIf extends NodoSentencia{
    NodoExp cond;
    NodoSentencia thenBody;
    NodoSentencia elseBody;

    private static int labelCounter = 0;

    public NodoIf(Token tkn, NodoExp cond, NodoSentencia thenBody, NodoSentencia elseBody) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public void check() {
        if(!cond.check().isCompatible(new BooleanType()))
            throw new SemanticException("La condición del if no es de tipo booleano", name, lineNumber);

        thenBody.check();
        elseBody.check();
    }

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public boolean guaranteeReturn(){
        return thenBody.guaranteeReturn() && elseBody.guaranteeReturn();
    }

    public void generate(){
        int currentLabel = labelCounter++;
        String elseLabel = "else_" + currentLabel;
        String endIfLabel = "end_if_" + currentLabel;

        cond.generate();

        if(!(elseBody instanceof NodoSentenciaVacia)){
            symbolTable.addInstruction("BF " + elseLabel);
            thenBody.generate();
            symbolTable.addInstruction("JUMP " + endIfLabel);

            symbolTable.addInstruction(elseLabel + ":");
            elseBody.generate();
            symbolTable.addInstruction(endIfLabel+":");


        }else {
            symbolTable.addInstruction("BF " + endIfLabel);
            thenBody.generate();
            symbolTable.addInstruction(endIfLabel + ":");
        }
    }
}
