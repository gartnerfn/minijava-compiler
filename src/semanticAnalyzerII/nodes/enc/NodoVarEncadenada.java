package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.entities.Attribute;
import semanticAnalyzerI.entities.Entity;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoVarEncadenada extends NodoEncadenado{
    public NodoVarEncadenada(Token tkn){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public Type check(Type previousType){
        Entity entity = symbolTable.existsEntity(previousType.name);

        if(entity == null)
            throw new SemanticException("Tipo '" + previousType.name + "' no es una clase válida", this.name, this.lineNumber);

        Attribute attr = entity.existsAttribute(name);

        if(attr == null)
            throw new SemanticException("Variable '" + name + "' no declarada en la clase " + previousType.name, name, lineNumber);

        if(!attr.isPublic && attr.declaredIn != symbolTable.currentEntity)
            throw new SemanticException("Variable '" + name + "' es privada y no puede ser accedida desde este ámbito", name, lineNumber);

        if(nextInTheChain != null)
            return nextInTheChain.check(attr.type);

        return attr.type;
    }

    public boolean isOperandWithCall(){
        if(nextInTheChain != null)
            return nextInTheChain.isOperandWithCall();

        return false;
    }

    public boolean isAssignable(){
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return true;
    }

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return false;
    }

    public void generate(){
        if(nextInTheChain != null)
            nextInTheChain.generate();
    }
}
