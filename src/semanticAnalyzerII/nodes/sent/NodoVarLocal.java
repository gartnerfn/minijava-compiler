package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.NullType;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoVarLocal extends Variable {
    NodoExp exp;
    Token assignOp;

    public NodoVarLocal(Token tkn, NodoExp exp, Token assignOp) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.exp = exp;

        this.assignOp = assignOp;
    }

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public void check(){
        type = exp.check();

        if(type instanceof NullType)
            throw new SemanticException("No se puede inferir el tipo de la variable local " + name + " porque el lado derecho el lado derecho de la asignacion es null", assignOp.lexeme(), assignOp.lineNumber());

        if(type instanceof VoidType)
            throw new SemanticException("No se puede inferir el tipo de la variable local " + name + " porque el lado derecho el lado derecho de la asignacion es void", assignOp.lexeme(), assignOp.lineNumber());

        symbolTable.addLocalVar(this);
    }
}
