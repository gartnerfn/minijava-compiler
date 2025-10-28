package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExpComp;
import src.Token;

public class Attribute extends Variable{
    boolean isPublic;
    NodoExpComp init;

    public Attribute(Token tkn, Type type, String visibilityModifier, NodoExpComp init){
        super(tkn, type);
        this.isPublic = !visibilityModifier.equals("private");
        this.init = init;
    }

    void isWellDeclared(){
        super.isWellDeclared();
    }

    public void check() {
        if (init != null) {
            Type initType = init.check();
            if (!initType.conformsTo(type))
                throw new SemanticException("Inicialización incompatible con el tipo de atributo " + name, init.value, init.lineNumber);
        }
    }
}
