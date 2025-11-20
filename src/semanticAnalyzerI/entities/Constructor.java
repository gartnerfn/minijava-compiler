package semanticAnalyzerI.entities;

import src.Token;

public class Constructor extends Routine{
    public Constructor(Token tkn, String visibilityModifier){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        INITIAL_PARAMETER_OFFSET = 4; // 0 = 1vl, 1 = ed, 2 = pr, 3 = this
        parameterCount = 0;
        localVarOffset = 0;

        this.isPublic = !visibilityModifier.equals("private");
    }

    public void isWellDeclared(){
        super.isWellDeclared();
    }

    public void generate(){
        symbolTable.addInstruction("lblConstructor" + parameters.size() + "@" +  this.name + ":");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");

        super.generate();

        symbolTable.addInstruction("STOREFP");
        //TODO: cantidad de parametros, no 1
        symbolTable.addInstruction("RET 1");
    }
}
