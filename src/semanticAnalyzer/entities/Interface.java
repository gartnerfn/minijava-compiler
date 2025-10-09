package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import src.Token;

public class Interface extends Entity{

    public Interface(Token tkn){
        super(tkn);
        this.ancestorInheritance = null;

        this.isAbstract = true;
    }

    public void inheritsFrom(Token ancestor){
        if(ancestor != null)
            ancestorInheritance = ancestor.lexeme();
    }

    public void addMethod(Method method){
        if(methods.containsKey(method.name))
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);

        if(!method.isFinal && !method.isStatic)
            method.isAbstract = true;

        methods.put(method.name, method);
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
                if(!attributes.containsKey(attribute.name))
                    attributes.put(attribute.name, attribute);
            }

            for(Method method : ancestorInheritanceInterface.methods.values()){
                if(!methods.containsKey(method.name))
                    methods.put(method.name, method);
            }
        }

        isConsolidated = true;
    }
}
