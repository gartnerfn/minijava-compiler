package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaMetodo extends NodoReferencia{
    List<NodoExp> args;

    public NodoLlamadaMetodo(Token tkn, List<NodoExp> args){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.args = args;
    }

    public Type check(){
        Method method = symbolTable.currentEntity.existsMethod(this.name, args.size());

        if(method == null)
            throw new SemanticException("Method does not exist", this.name, this.lineNumber);

        if(symbolTable.currentRoutine instanceof Method &&
                ((Method)symbolTable.currentRoutine).isStatic && !method.isStatic)
            throw new SemanticException("Cannot call non-static method from static context", this.name, this.lineNumber);

        if(method.returnType instanceof VoidType && nextInTheChain != null)
            throw new SemanticException("Cannot chain after a void method", nextInTheChain.name, nextInTheChain.lineNumber);

        if(!method.isPublic && method.declaredIn != symbolTable.currentEntity)
            throw new SemanticException("Method is private and cannot be accessed from this scope", this.name, this.lineNumber);

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

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return true;
    }
}
