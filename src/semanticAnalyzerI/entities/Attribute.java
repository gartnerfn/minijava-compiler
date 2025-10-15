package semanticAnalyzerI.entities;

import semanticAnalyzerI.types.Type;
import src.Token;

public class Attribute extends Variable{
    boolean isPublic;

    public Attribute(Token tkn, Type type, String visibilityModifier){
        super(tkn, type);
        this.isPublic = !visibilityModifier.equals("private");
    }

    void isWellDeclared(){
        super.isWellDeclared();
    }
}
