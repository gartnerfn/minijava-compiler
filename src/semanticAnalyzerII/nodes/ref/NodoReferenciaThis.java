package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoReferenciaThis extends NodoReferencia{
    public NodoReferenciaThis(Token thisTkn){
        this.value = symbolTable.currentEntity.name;
        this.lineNumber = thisTkn.lineNumber();
    }

    public Type check() {
//        if(symbolTable.currentRoutine)
//            throw new SemanticException("Error semantico en linea " + token.getLineNumber() + ": no se puede usar 'this' en un metodo estatico", token.getLexeme(), token.getLineNumber());

        Type thisType = new ReferenceType(new Token("classId", value, lineNumber));

        if(nextInTheChain != null)
            return nextInTheChain.check(thisType);

        return thisType;
    }

}
