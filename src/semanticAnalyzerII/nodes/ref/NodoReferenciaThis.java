package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoReferenciaThis extends NodoReferencia{
    public NodoReferenciaThis(Token thisTkn){
        this.value = symbolTable.currentEntity.name;
        this.name = thisTkn.lexeme();
        this.lineNumber = thisTkn.lineNumber();
    }

    public Type check() {
        if(symbolTable.currentRoutine instanceof Method &&
                ((Method)symbolTable.currentRoutine).isStatic)
            throw new SemanticException("Error semantico en linea " + this.lineNumber + ": no se puede usar 'this' en un metodo estatico", this.name, this.lineNumber);

        Type thisType = new ReferenceType(new Token("classId", value, lineNumber));

        if(nextInTheChain != null)
            return nextInTheChain.check(thisType);

        return thisType;
    }

    public void generate(){

    }
}
