package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

public class NodoReturn extends NodoSentencia{

    NodoExp returnExp;

    public NodoReturn(Token tkn, NodoExp returnExp){
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();

        this.returnExp = returnExp;
    }

    public void check(){
        Method method = (Method) symbolTable.currentRoutine;
        Type methodType = method.returnType;

        Type returnType = returnExp.check();

        if(!returnType.conformsTo(methodType))
            throw new SemanticException("Tipo de retorno incompatible en el método " + method.name + ", el valor de retorno " + returnType.name + " no conforma con " + methodType.name, name, lineNumber);
    }
    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public boolean guaranteeReturn(){
        return true;
    }
}
