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
//        if(symbolTable.getCurrentInvocable().isStaticMethod())
//            throw new SemanticException("Error semantico en linea " + token.getLineNumber() + ": no se puede usar 'this' en un metodo estatico", token.getLexeme(), token.getLineNumber());

        Type thisType = new ReferenceType(new Token("classId", value, lineNumber));
//        if(optChaining != null) {
//            return optChaining.check(thisType);
//        }
        return thisType;
    }

    public boolean isStatement(){
        return false;
    }

}
