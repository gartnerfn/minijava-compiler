package semanticAnalyzerII.nodes.lit;

import semanticAnalyzerI.types.ReferenceType;
import src.Token;

public class NodoStringLit extends NodoLit {
    public NodoStringLit(Token tkn){
        super(tkn, new ReferenceType(new Token("classId", "String", tkn.lineNumber())));
    }
}
