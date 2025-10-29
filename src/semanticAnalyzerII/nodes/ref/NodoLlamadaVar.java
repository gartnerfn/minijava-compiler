package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Attribute;
import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import src.Token;

public class NodoLlamadaVar extends NodoReferencia{
    public NodoLlamadaVar(Token tkn) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public Type check(){
        Variable variable = symbolTable.existsVar(name);

        if(variable == null)
            throw new SemanticException("Variable '" + name + "' no declarada", name, lineNumber);

        if(variable instanceof Attribute && !((Attribute) variable).isPublic && ((Attribute) variable).declaredIn != symbolTable.currentEntity)
            throw new SemanticException("Variable '" + name + "' es privada y no puede ser accedida desde este ámbito", name, lineNumber);

//        if (nextInTheChain != null)
//            return nextInTheChain.checkAssignable(type);

        return variable.type;
    }

    public boolean checkAssignable() {
        return true;
    }
}
