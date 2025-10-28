package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;

public class NodoLlamadaMetodoEstatico extends NodoReferencia{

    public NodoLlamadaMetodoEstatico(){

    }

    public Type check(){
//        if(((Method) symbolTable.currentRoutine).isStatic && !method.isStatic)
//            throw new SemanticException("Cannot call non-static method from static context", this.name, this.lineNumber);
//
        return null;
    }
}
