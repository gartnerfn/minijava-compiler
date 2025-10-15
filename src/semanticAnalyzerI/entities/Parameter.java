package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import src.Token;

public class Parameter extends Variable{
    public Parameter(Token tkn, Type type) {
        super(tkn, type);
    }

    void isWellDeclared(){
        super.isWellDeclared();
    }
}
