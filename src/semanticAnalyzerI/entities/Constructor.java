package semanticAnalyzerI.entities;

import src.Token;

public class Constructor extends Routine{
    public Constructor(Token tkn, String visibilityModifier){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.isPublic = !visibilityModifier.equals("private");


    }

    public void isWellDeclared(){
        super.isWellDeclared();
    }
}
