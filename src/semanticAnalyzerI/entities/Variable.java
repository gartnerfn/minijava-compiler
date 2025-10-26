package semanticAnalyzerI.entities;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import src.Token;

public abstract class Variable {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public String name;
    public int lineNumber;
    public Type type;

    public Variable(Token tkn, Type type){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.type = type;
    }

    void isWellDeclared(){
        if(type instanceof ReferenceType){
            Class referenceType = symbolTable.existsClass(type.name);

            if(referenceType == null)
                throw new SemanticException("La clase utilizada como tipo no existe.", type.name, type.lineNumber);
        }
    }
}
