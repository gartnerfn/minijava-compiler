package semanticAnalyzerI.entities;

import src.Token;

public class Constructor extends Routine{
    public Constructor(Token tkn, String visibilityModifier){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        INITIAL_PARAMETER_OFFSET = 4; // 0 = 1vl, 1 = ed, 2 = pr, 3 = this
        parameterCount = 0;

        this.isPublic = !visibilityModifier.equals("private");
    }

    public void isWellDeclared(){
        super.isWellDeclared();
    }

    public void generate(){
        symbolTable.currentRoutine = this;

        symbolTable.addInstruction("lblConstructor" + parameters.size() + "@" +  this.name + ":");

        super.generate();

        symbolTable.addInstruction("RET " + (parameters.size() + 1));

    }
}
