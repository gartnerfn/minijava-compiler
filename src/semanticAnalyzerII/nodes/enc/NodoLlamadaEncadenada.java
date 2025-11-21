package semanticAnalyzerII.nodes.enc;

import semanticAnalyzerI.entities.Entity;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaEncadenada extends NodoEncadenado{
    Entity entity;
    Method method;
    List<NodoExp> args;

    public NodoLlamadaEncadenada(Token tkn, List<NodoExp> args){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
        this.args = args;
    }

    public Type check(Type previousType){
        entity = symbolTable.existsEntity(previousType.name);
        if(entity == null)
            throw new SemanticException("Class " + previousType.name + " does not exist", this.name, this.lineNumber);

        method = symbolTable.existsEntity(previousType.name).existsMethod(name, args.size());

        if(method == null)
            throw new SemanticException("Method " + this.name + " does not exist in class " + previousType.name, this.name, this.lineNumber);

        if(!method.isPublic && method.declaredIn != symbolTable.currentEntity)
            throw new SemanticException("Method " + this.name + " is private and cannot be accessed from this scope", this.name, this.lineNumber);

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

    public boolean isOperandWithCall(){
        if(nextInTheChain != null)
            return nextInTheChain.isOperandWithCall();

        return true;
    }


    public void generate(){
        if (method.isStatic) {
            symbolTable.addInstruction("POP");

            if (!(method.returnType instanceof VoidType))
                symbolTable.addInstruction("RMEM 1");

            for (NodoExp arg : args)
                arg.generate();

            symbolTable.callStaticMethod(method);
        } else {

        int offset = ((semanticAnalyzerI.entities.Class)entity).getMethodOffset(method);
        if(!(method.returnType instanceof VoidType)) {
            symbolTable.addInstruction("RMEM 1");
            symbolTable.addInstruction("SWAP");
        }
        for(NodoExp arg : args) {
            arg.generate();
            symbolTable.addInstruction("SWAP");
        }

        symbolTable.addInstruction("DUP");
        symbolTable.addInstruction("LOADREF 0 ");
        symbolTable.addInstruction("LOADREF " + offset);

        symbolTable.callMethod(method);


        }

        if (nextInTheChain != null)
            nextInTheChain.generate();
    }

    public void generate(boolean isLeftSide) {

        if (method.isStatic) {
            symbolTable.addInstruction("POP");

            if (!(method.returnType instanceof VoidType))
                symbolTable.addInstruction("RMEM 1");

            for (NodoExp arg : args)
                arg.generate();

            symbolTable.callStaticMethod(method);
        } else {

            int offset = ((semanticAnalyzerI.entities.Class)entity).getMethodOffset(method);
            if(!(method.returnType instanceof VoidType)) {
                symbolTable.addInstruction("RMEM 1");
                symbolTable.addInstruction("SWAP");
            }
            for(NodoExp arg : args) {
                arg.generate();
                symbolTable.addInstruction("SWAP");
            }

            symbolTable.addInstruction("DUP");
            symbolTable.addInstruction("LOADREF 0 ");
            symbolTable.addInstruction("LOADREF " + offset);

            symbolTable.callMethod(method);


        }

        if(nextInTheChain != null) {
            nextInTheChain.generate(isLeftSide);
        }
    }
}
