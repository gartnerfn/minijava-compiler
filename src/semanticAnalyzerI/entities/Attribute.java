package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExpComp;
import semanticAnalyzerII.nodes.exp.NodoExpVacia;
import semanticAnalyzerII.nodes.ref.NodoLlamadaVar;
import src.Token;

public class Attribute extends Variable{
    public boolean isPublic;
    public Entity declaredIn;
    NodoExpComp init;
    Token assignOp;

    public Attribute(Token tkn, Type type, String visibilityModifier, NodoExpComp init, Entity declaredIn, Token assignOp){
        this(tkn, type, visibilityModifier, init, declaredIn);
        this.assignOp = assignOp;
    }

    public Attribute(Token tkn, Type type, String visibilityModifier, NodoExpComp init, Entity declaredIn){
        super(tkn, type);
        this.declaredIn = declaredIn;
        this.isPublic = !visibilityModifier.equals("private");

        if(init instanceof NodoLlamadaVar && symbolTable.existsVar(init.name) == null)
            throw new SemanticException("La variable a la que se hace referencia no existe", init.name, init.lineNumber);

        this.init = init;
    }

    void isWellDeclared(){
        super.isWellDeclared();
    }

    public void check(){
        if (!(init instanceof NodoExpVacia)) {
            Type initType = init.check();

            if (!initType.conformsTo(type))
                throw new SemanticException("Inicialización incompatible con el tipo de atributo" + name, assignOp.lexeme(), assignOp.lineNumber());
        }
    }
}
