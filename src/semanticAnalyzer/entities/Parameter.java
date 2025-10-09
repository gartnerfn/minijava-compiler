package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import semanticAnalyzer.types.ReferenceType;
import semanticAnalyzer.types.Type;
import src.Token;

public class Parameter {
    SymbolTable symbolTable = SymbolTable.getInstance();

    String name;
    int lineNumber;
    Type type;

    public Parameter(Token tkn, Type type) {
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
