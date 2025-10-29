package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.entities.Entity;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaEncadenada extends NodoEncadenado{
    List<NodoExp> args;

    public NodoLlamadaEncadenada(Token tkn, List<NodoExp> args){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.args = args;
    }

    public Type check(Type previousType){
        Entity entity = symbolTable.existsEntity(previousType.name);
        if(entity == null)
            throw new SemanticException("Class " + previousType.name + " does not exist", this.name, this.lineNumber);

        Method method = symbolTable.existsEntity(previousType.name).existsMethod(name, args.size());

        if(method == null)
            throw new SemanticException("Method " + this.name + " does not exist in class " + previousType.name, this.name, this.lineNumber);

        if(!method.isPublic && method.declaredIn != symbolTable.currentEntity)
            throw new SemanticException("Method " + this.name + " is private and cannot be accessed from this scope", this.name, this.lineNumber);

        if(method.parameters.size() != args.size())
            throw new SemanticException("Method parameter count mismatch", this.name, this.lineNumber);

        for(int i = 0; i < args.size(); i++){
            NodoExp arg = args.get(i);
            Parameter param = method.parameterList.get(i);

            Type argType = arg.check();
            Type paramType = param.type;

            if(!argType.conformsTo(paramType))
                throw new SemanticException("Method parameter type mismatch", this.name, this.lineNumber);
        }

        if(nextInTheChain != null)
            return nextInTheChain.check(method.returnType);

        return method.returnType;
    }

    public boolean isAssignable(){
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return false;
    }

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return true;
    }
}
