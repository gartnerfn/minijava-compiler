package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import src.Token;

public class Method extends Routine{

    public boolean isAbstract;
    public boolean isFinal;
    public boolean isStatic;

    public Type returnType;

    public Method(Token tkn, Type returnType, String typeModifier, String visibilityModifier){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.returnType = returnType;

        isPublic = !visibilityModifier.equals("private");

        this.isAbstract = false;
        this.isFinal = false;
        this.isStatic = false;

        switch(typeModifier){
            case "abstract":
                isAbstract = true;
                break;
            case "final":
                isFinal = true;
                break;
            case "static":
                isStatic = true;
                break;
        }
    }

    public boolean hasBody(){
        return block != null;
    }

    void isWellDeclared(){
        if(returnType instanceof ReferenceType){
            Class referenceType = symbolTable.existsClass(returnType.name);

            if(referenceType == null)
                throw new SemanticException("La clase utilizada como tipo de retorno no existe.", returnType.name, returnType.lineNumber);
        }

        if(!isAbstract && !hasBody())
            throw new SemanticException("Los metodos no abstractos deben tener cuerpo.", name, lineNumber);

        if(isAbstract && hasBody())
            throw new SemanticException("Los metodos abstractos no pueden tener cuerpo.", name, lineNumber);

        super.isWellDeclared();
    }
}
