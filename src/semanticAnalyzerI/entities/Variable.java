package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.sent.NodoSentencia;
import src.Token;

public abstract class Variable extends NodoSentencia {
    public SymbolTable symbolTable = SymbolTable.getInstance();

    public String name;
    public int lineNumber;
    public Type type;

    public int offset;

    public Variable(){}

    public Variable(Token tkn, Type type){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.type = type;
    }

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    void isWellDeclared(){
        if(type instanceof ReferenceType){
            Class referenceType = symbolTable.existsClass(type.name);

            if(referenceType == null)
                throw new SemanticException("La clase utilizada como tipo no existe.", type.name, type.lineNumber);
        }
    }

    public boolean guaranteeReturn(){
        return false;
    }

    public void generate(){}
}
