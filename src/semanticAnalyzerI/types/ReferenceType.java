package semanticAnalyzerI.types;

import semanticAnalyzerI.SymbolTable;
import src.Token;

public class ReferenceType extends Type{
    SymbolTable symbolTable = SymbolTable.getInstance();

    public ReferenceType(Token tkn){
        super(tkn.lexeme(), tkn.lineNumber());
    }

    public boolean isCompatible(Type type){
        return false;
    }

    public boolean conformsTo(Type type){
        if (!(type instanceof ReferenceType))
            return false;

        if (this.name.equals(type.name))
            return true;

        semanticAnalyzerI.entities.Class currentClass = (semanticAnalyzerI.entities.Class) symbolTable.currentEntity;
        while (currentClass.hasParentInheritance() || currentClass.hasParentImplementation()) {
            String parentInheritance = currentClass.getParentInheritance();
            String parentImplementation = currentClass.getParentImplementation();
            String parent = parentInheritance != null ? parentInheritance : parentImplementation;

            if (parent.equals(type.name))
                return true;

            currentClass = symbolTable.getClass(parent);
        }

        return false;
    }
}