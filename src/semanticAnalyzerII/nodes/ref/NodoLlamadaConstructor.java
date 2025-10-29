package semanticAnalyzerII.nodes.ref;

import semanticAnalyzerI.entities.Constructor;
import semanticAnalyzerI.entities.Entity;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.types.ReferenceType;
import semanticAnalyzerI.types.Type;
import semanticAnalyzerII.nodes.exp.NodoExp;
import src.Token;

import java.util.List;

public class NodoLlamadaConstructor extends NodoReferencia{
    private List<NodoExp> args;

    public NodoLlamadaConstructor(Token classId, List<NodoExp> args) {
        this.name = classId.lexeme();
        this.lineNumber = classId.lineNumber();
        this.args = args;
    }

    public Type check() {
        Entity entity = symbolTable.existsEntity(this.name);
        if(entity == null)
            throw new SemanticException("Class does not exist", this.name, this.lineNumber);

        Constructor constructor = entity.existsConstructor(this.name, args.size());

        if(constructor == null)
            throw new SemanticException("Contructor does not exist", this.name, this.lineNumber);

        if(!constructor.isPublic && !constructor.name.equals(symbolTable.currentEntity.name))
            throw new SemanticException("Contructor is private and cannot be accessed from this scope", this.name, this.lineNumber);


        for(int i = 0; i < args.size(); i++){
            NodoExp arg = args.get(i);
            Parameter param = constructor.parameterList.get(i);

            Type argType = arg.check();
            Type paramType = param.type;

            if(!argType.conformsTo(paramType))
                throw new SemanticException("Constructor parameter type mismatch", this.name, this.lineNumber);
        }

        if(nextInTheChain != null)
            return nextInTheChain.check(new ReferenceType(new Token("classId", this.name, this.lineNumber)));

        return new ReferenceType(new Token("classId", this.name, this.lineNumber));
    }

    public boolean canBeStatement(){
        if(nextInTheChain != null)
            return nextInTheChain.canBeStatement();

        return true;
    }
}
