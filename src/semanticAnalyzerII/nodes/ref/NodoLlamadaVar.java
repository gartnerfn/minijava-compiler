package semanticAnalyzerII.nodes.ref;

import org.w3c.dom.Attr;
import semanticAnalyzerI.entities.Attribute;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.entities.Variable;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.sent.NodoVarLocal;
import src.Token;

public class NodoLlamadaVar extends NodoReferencia{
    Variable variable;

    public NodoLlamadaVar(Token tkn) {
        this.name = tkn.lexeme();
        this.lineNumber = tkn.lineNumber();
    }

    public Type check(){
        variable = symbolTable.existsVar(name);

        if(variable == null)
            throw new SemanticException("Variable '" + name + "' no declarada", name, lineNumber);

        if(variable instanceof Attribute && !((Attribute) variable).isPublic && ((Attribute) variable).declaredIn != symbolTable.currentEntity)
            throw new SemanticException("El atributo '" + name + "' es privada y no puede ser accedida desde este ámbito", name, lineNumber);

        if(variable instanceof Attribute && symbolTable.currentRoutine instanceof Method && ((Method) symbolTable.currentRoutine).isStatic)
            throw new SemanticException("El atributo no estático'" + name + "' no puede ser accedido desde un metodo estatico", name, lineNumber);

        if (nextInTheChain != null)
            return nextInTheChain.check(variable.type);

        return variable.type;
    }

    public boolean isAssignable() {
        if(nextInTheChain != null)
            return nextInTheChain.isAssignable();

        return true;
    }

    public void generate(){
        System.out.println("DEBUG VarCallNode: Generando código para variable '" + name + "'");

        boolean isLeftSide = false;

       if(variable instanceof Attribute){
              int offset = symbolTable.currentClass.getAttributeOffset((Attribute) variable);
           System.out.println("  -> Cargando atributo '" + name + "' de la clase '" + ((Attribute) variable).declaredIn.name + "'");
           symbolTable.addInstruction("LOAD 3");
           if(!isLeftSide || nextInTheChain != null)
               symbolTable.addInstruction("LOADREF " + offset);
           else {
               symbolTable.addInstruction("SWAP");
               symbolTable.addInstruction("STOREREF " + offset);
           }
       }
       else if (variable instanceof Parameter || variable instanceof NodoVarLocal){
           int offset = symbolTable.currentRoutine.getOffset(variable.name);

           System.out.println("  -> Cargando variable local/parametro '" + name + "' con offset: " + offset);
           if(!isLeftSide || nextInTheChain != null)
               symbolTable.addInstruction("LOAD " + offset);
           else {
               symbolTable.addInstruction("STORE " + offset);
           }
       }

        if(nextInTheChain != null)
            nextInTheChain.generate();
    }
}
