package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoSentenciaConExp extends NodoSentencia{
    NodoExp exp;
    Token semicolon;

    public NodoSentenciaConExp(Token tkn, NodoExp exp, Token semicolon) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.semicolon = semicolon;

        this.exp = exp;
    }
    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public void check() {
        exp.check();

        if (!exp.canBeStatement())
            throw new SemanticException("La expresion no puede ser una sentencia", semicolon.lexeme(), semicolon.lineNumber());
    }

    public boolean guaranteeReturn(){
        return false;
    }

    public void generate(){
        exp.generate();
        symbolTable.addInstruction("POP");
    }
}
