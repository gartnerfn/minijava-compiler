package semanticAnalyzerI.types;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Entity;
import src.Token;

public class ReferenceType extends Type{
    SymbolTable symbolTable = SymbolTable.getInstance();

    public ReferenceType(Token tkn){
        super(tkn.lexeme(), tkn.lineNumber());
    }

    public boolean isCompatible(Type type){
        if (type instanceof NullType) return true;
        if (!(type instanceof ReferenceType)) return false;

        return this.conformsTo(type) || type.conformsTo(this);
    }

    public boolean conformsTo(Type type){
        if(type instanceof NullType)
            return true;

        if (!(type instanceof ReferenceType))
            return false;

        if (this.name.equals(type.name))
            return true;

        Entity thisEntity = symbolTable.getEntity(this.name);

        while (thisEntity.hasParentInheritance() || thisEntity.hasParentImplementation()) {
            String parentInheritance = thisEntity.getParentInheritance();
            String parentImplementation = thisEntity.getParentImplementation();
            String parent = parentInheritance != null ? parentInheritance : parentImplementation;

            if (parent.equals(type.name))
                return true;

            thisEntity = symbolTable.getEntity(parent);
        }

        return false;
    }
}