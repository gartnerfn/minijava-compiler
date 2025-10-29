package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Method;
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
//        if(((Method) symbolTable.currentRoutine).isStatic && !method.isStatic)
//            throw new SemanticException("Cannot call non-static method from static context", this.name, this.lineNumber);

        return null;
    }
}
