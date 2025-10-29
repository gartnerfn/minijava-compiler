package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Entity;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaMetodoEstatico extends NodoReferencia{
    private Token classId;
    private Token methodId;

    List<NodoExp> args;

    public NodoLlamadaMetodoEstatico(Token classId, Token methodId, List<NodoExp> args){
        this.classId = classId;
        this.methodId = methodId;
        this.args = args;
    }

    public Type check(){
        Entity entity = symbolTable.existsEntity(this.classId.lexeme());

        if(entity == null)
            throw new SemanticException("Class does not exist", this.classId.lexeme(), this.classId.lineNumber());

        Method method = entity.existsMethod(this.methodId.lexeme(), args.size());

        if(method == null)
            throw new SemanticException("Static method does not exist", this.methodId.lexeme(), this.methodId.lineNumber());

        for(int i = 0; i < args.size(); i++){
            NodoExp arg = args.get(i);
            Parameter param = method.parameterList.get(i);

            Type argType = arg.check();
            Type paramType = param.type;

            if(!argType.conformsTo(paramType))
                throw new SemanticException("Static method parameter type mismatch", methodId.lexeme(), methodId.lineNumber());
        }

        if(!method.isStatic)
            throw new SemanticException("Method is not static", this.methodId.lexeme(), this.methodId.lineNumber());

        return method.returnType;
    }

   public boolean canBeStatement(){
       if(nextInTheChain != null)
           return nextInTheChain.canBeStatement();

        return true;
    }
}
