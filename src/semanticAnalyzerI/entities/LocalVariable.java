package semanticAnalyzerI.entities;

import semanticAnalyzerI.types.Type;
import src.Token;

public class LocalVariable extends Variable{
    public LocalVariable(Token tkn, Type type) {
        super(tkn, type);
    }

    void isWellDeclared(){
        super.isWellDeclared();
    }
}
