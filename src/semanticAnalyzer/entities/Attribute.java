package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import semanticAnalyzer.types.ReferenceType;
import semanticAnalyzer.types.Type;
import src.Token;

public class Attribute {
    SymbolTable symbolTable = SymbolTable.getInstance();

    boolean isPublic;

    String name;
    int lineNumber;
    Type type;

    public Attribute(Token tkn, Type type, String visibilityModifier){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.type = type;

        this.isPublic = !visibilityModifier.equals("private");
    }

    void isWellDeclared(){
        if(type instanceof ReferenceType){
            Class referenceType = symbolTable.existsClass(type.name);

            if(referenceType == null)
                throw new SemanticException("La clase utilizada como tipo no existe.", type.name, type.lineNumber);
        }
    }
}
