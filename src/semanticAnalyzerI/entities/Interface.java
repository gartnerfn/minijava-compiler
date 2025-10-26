package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import src.Token;

public class Interface extends Entity{
    public Interface(Token tkn){
        super(tkn);
        this.ancestorInheritance = null;

        this.isAbstract = true;
    }

    public void inheritsFrom(Token ancestor){
        super.inheritsFrom(ancestor);
    }

    public void addMethod(Method method){
        if(method.isPublic && !method.isFinal && !method.isStatic)
            method.isAbstract = true;

        super.addMethod(method);
    }

    public void isWellDeclared(){
        if (ancestorInheritance != null) {
            Class ancestorInheritanceClass = symbolTable.existsClass(ancestorInheritance);
            Interface ancestorInheritanceInterface = symbolTable.existsInterface(ancestorInheritance);

            if(ancestorInheritanceClass != null)
                throw new SemanticException("No se esta extendiendo a una interfaz.", ancestorInheritance, lineNumber);

            if(ancestorInheritanceInterface == null)
                throw new SemanticException("La interfaz que se extiende no existe.", ancestorInheritance, lineNumber);

            if(ancestorInheritance.equals(name))
                throw new SemanticException("Herencia circular entre la misma interfaz.", ancestorInheritance, lineNumber);

            while(ancestorInheritanceInterface.ancestorInheritance != null ){
                if( ancestorInheritanceInterface.ancestorInheritance.equals(name))
                    throw new SemanticException("Herencia circular.", ancestorInheritance, lineNumber);

                ancestorInheritanceInterface = symbolTable.existsInterface(ancestorInheritanceInterface.ancestorInheritance);
            }
        }

        for(Attribute attribute : attributes.values())
            attribute.isWellDeclared();

        for(Method method : methods.values()){
            if(method.isFinal)
                throw new SemanticException("Una interfaz no puede tener metodos finales.", method.name, method.lineNumber);

            method.isWellDeclared();
        }
    }

    public void consolidate(){
        if(ancestorInheritance != null){
            Interface ancestorInheritanceInterface = symbolTable.existsInterface(ancestorInheritance);

            if(!ancestorInheritanceInterface.isConsolidated)
                ancestorInheritanceInterface.consolidate();

            for(Attribute attribute : ancestorInheritanceInterface.attributes.values()){
                addInheritedAttribute(attribute, ancestorInheritance);
            }

            for(Method method : ancestorInheritanceInterface.methods.values()){
                if(!method.isStatic && method.isPublic && existsMethod(method) == null)
                    addMethod(method);
            }
        }

        isConsolidated = true;
    }
}