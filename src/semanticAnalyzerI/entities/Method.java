package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import src.Token;

public class Method extends Routine{
    public boolean isAbstract;
    public boolean isFinal;
    public boolean isStatic;

    public Type returnType;

    public Method(Token tkn, Type returnType, String typeModifier, String visibilityModifier, Entity declaredIn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.returnType = returnType;

        isPublic = !visibilityModifier.equals("private");

        this.isAbstract = false;
        this.isFinal = false;
        this.isStatic = false;

        INITIAL_PARAMETER_OFFSET = 4; // 0 = 1vl, 1 = ed, 2 = pr, 3 = this
        parameterCount = 0;

        switch(typeModifier){
            case "abstract":
                isAbstract = true;
                break;
            case "final":
                isFinal = true;
                break;
            case "static":
                isStatic = true;
                INITIAL_PARAMETER_OFFSET--; // If the method is static it does not have "this" reference
                break;
        }

        this.declaredIn = declaredIn;
    }

    public boolean hasBody(){
        return !(block instanceof NodoBloqueNulo);
    }

    public void isWellDeclared(){
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

    public void check(){
        super.check();

        if(!(returnType instanceof VoidType) && !block.guaranteeReturn())
            throw new SemanticException("Missing return statement in routine '" + name + "'", name, lineNumber);
    }

    public void generate(){
        symbolTable.currentRoutine = this;

        symbolTable.addInstruction("lblMethod_" + this.name + this.parameters.size() + "@" + this.declaredIn.name + ":");
        super.generate();
    }
}
