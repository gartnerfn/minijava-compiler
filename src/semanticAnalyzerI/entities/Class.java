package semanticAnalyzerI.entities;

import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerII.nodes.sent.NodoBloque;
import src.Token;

public class Class extends Entity{
    public boolean isFinal;
    public boolean isStatic;

    public Class(Token tkn){
        super(tkn);

        this.isAbstract = false;
        this.isFinal = false;
        this.isStatic = false;

        this.ancestorInheritance = "Object";
    }

    public Class(Token tkn, String typeModifier){
        this(tkn);

        switch(typeModifier){
            case "abstract":
                isAbstract = true;
                break;
            case "final":
                isFinal = true;
                break;
            case "static":
                isStatic = true;
                break;
        }
    }

    public void inheritsFrom(Token ancestor){
        super.inheritsFrom(ancestor);
    }

    public void implementsFrom(Token ancestor){
        super.implementsFrom(ancestor);
    }

    public void addMethod(Method method){
        super.addMethod(method);
    }

    public void addConstructor(Constructor constructor){
        if(isAbstract)
            throw new SemanticException("Abstract classes cant have a constructor.", constructor.name, constructor.lineNumber);

        super.addConstructor(constructor);
    }

    public boolean isConcrete(){
        return !isAbstract;
    }

    public boolean hasConstructor(){
        return !constructors.isEmpty();
    }

    private void createConstructor(){
        Constructor constructor = new Constructor(new Token("classId", this.name, 0), "");
        constructor.addBlock(new NodoBloque());

        addConstructor(constructor);
    }

    public void isWellDeclared(){
        if(isStatic)
            throw new SemanticException("Una clase no puede ser static fuera de otra clase.", this.name, this.lineNumber);

        if(isConcrete() && !hasConstructor())
            createConstructor();

        Interface ancestorInheritanceInterface = symbolTable.existsInterface(ancestorInheritance);
        Class ancestorInheritanceClass = symbolTable.existsClass(ancestorInheritance);

        if(ancestorInheritanceInterface != null)
            throw new SemanticException("Una clase no puede extender una interfaz.", ancestorInheritance, lineNumber);

        if(ancestorInheritanceClass == null)
            throw new SemanticException("La clase que se extiende no existe.", ancestorInheritance, lineNumber);

        if(ancestorInheritanceClass.isFinal)
            throw new SemanticException("Una clase no puede extender una clase final.", ancestorInheritance, lineNumber);

        if(ancestorInheritanceClass.isStatic)
            throw new SemanticException("Una clase no puede extender una clase static.", ancestorInheritance, lineNumber);

        if(this.isAbstract && ancestorInheritanceClass.isConcrete())
            throw new SemanticException("Una clase abstracta no puede extender una clase concreta.", ancestorInheritance, lineNumber);

        if(ancestorInheritance.equals(name))
            throw new SemanticException("Herencia circular entre la misma clase.", ancestorInheritance, lineNumber);

        while(ancestorInheritanceClass.ancestorInheritance != null){
            if(ancestorInheritanceClass.ancestorInheritance.equals(name))
                throw new SemanticException("Herencia circular.", ancestorInheritance, lineNumber);

            ancestorInheritanceClass = symbolTable.existsClass(ancestorInheritanceClass.ancestorInheritance);
        }

        if(ancestorImplementation != null){
            Interface ancestorImplementationInterface = symbolTable.existsInterface(ancestorImplementation);
            Class ancestorImplementationClass= symbolTable.existsClass(ancestorImplementation);

            if(ancestorImplementationClass != null)
                throw new SemanticException("Una clase no puede implementar una clase.", ancestorImplementation, lineNumber);

            if(ancestorImplementationInterface == null)
                throw new SemanticException("La interfaz que se implementa no existe.", ancestorImplementation, lineNumber);
        }

        for(Attribute attribute : attributes.values())
            attribute.isWellDeclared();

        for(Method method : methods.values()){
            if(!this.isAbstract && method.isAbstract)
                throw new SemanticException("Una clase concreta no puede tener metodos abstractos.", method.name, method.lineNumber);

            method.isWellDeclared();
        }

        for(Constructor constructor : constructors.values()){
            if(!constructor.name.equals(this.name))
                throw new SemanticException("El constructor debe tener el mismo nombre que la clase.", constructor.name, constructor.lineNumber);

            constructor.isWellDeclared();
        }
    }

    public void consolidate(){
        Class ancestorInheritanceClass = symbolTable.existsClass(ancestorInheritance);

        if(!ancestorInheritanceClass.isConsolidated)
            ancestorInheritanceClass.consolidate();

        for (Attribute attribute : ancestorInheritanceClass.attributes.values()) {
            addInheritedAttribute(attribute, ancestorInheritance);
        }

        for (Method method : ancestorInheritanceClass.methods.values()) {
            Method thisMethod = existsMethod(method);

            if (thisMethod == null) {
                if(method.isAbstract){
                    if(!this.isAbstract)
                        throw new SemanticException("La clase " + this.name + " no implementa todos los metodos heredados.", method.name, method.lineNumber);
                    else
                        addMethod(method);
                } else
                    addMethod(method);
            } else {
                if(method.isFinal){
                    throw new SemanticException("El metodo heredado es final y no puede ser redefinido.", thisMethod.name, thisMethod.lineNumber);
                } else if (method.isPublic && !thisMethod.isPublic){
                    throw new SemanticException("El metodo redefinido no puede reducir la visibilidad del metodo heredado.", thisMethod.name, thisMethod.lineNumber);
                } else if(thisMethod.isStatic && !method.isStatic){
                    throw new SemanticException("No se puede redefinir un metodo no estatico convirtiendolo en estatico.", thisMethod.name, thisMethod.lineNumber);
                } else {
                    if (!thisMethod.returnType.name.equals(method.returnType.name))
                        throw new SemanticException("El metodo heredado debe tener el mismo tipo de retorno.", thisMethod.name, thisMethod.lineNumber);

                    Object[] thisParameters = thisMethod.parameters.values().toArray();
                    Object[] ancestorParameters = method.parameters.values().toArray();

                    for (int i = 0; i < thisParameters.length; i++) {
                        Parameter thisParameter = (Parameter) thisParameters[i];
                        Parameter ancestorParameter = (Parameter) ancestorParameters[i];

                        if (!thisParameter.type.name.equals(ancestorParameter.type.name)) {
                            throw new SemanticException("El metodo heredado debe tener los mismos tipos de parametros.", thisMethod.name, thisMethod.lineNumber);
                        }
                    }
                }
            }
        }

        if(ancestorImplementation != null){
            Interface ancestorImplementationInterface = symbolTable.existsInterface(ancestorImplementation);

            for(Attribute attribute : ancestorImplementationInterface.attributes.values()){
                addInheritedAttribute(attribute, ancestorImplementation);
            }

            for(Method method : ancestorImplementationInterface.methods.values()){
                Method thisMethod = existsMethod(method);

                if (thisMethod == null) {
                    if(method.isAbstract) {
                        if (!this.isAbstract)
                            throw new SemanticException("La clase no implementa todos los metodos de la interfaz.", method.name, method.lineNumber);
                        else
                            addMethod(method);
                    } else
                        addMethod(method);
                } else {
                    if (method.isPublic && !thisMethod.isPublic){
                        throw new SemanticException("El metodo redefinido no puede reducir la visibilidad del metodo heredado.", thisMethod.name, thisMethod.lineNumber);
                    } else if(thisMethod.isStatic && !method.isStatic){
                        throw new SemanticException("No se puede redefinir un metodo no estatico convirtiendolo en estatico.", thisMethod.name, thisMethod.lineNumber);
                    } else if(!method.isStatic && method.isPublic){
                        if (!thisMethod.returnType.name.equals(method.returnType.name))
                            throw new SemanticException("El metodo heredado debe tener el mismo tipo de retorno.", thisMethod.name, thisMethod.lineNumber);

                        Object[] thisParameters = thisMethod.parameters.values().toArray();
                        Object[] ancestorParameters = method.parameters.values().toArray();

                        for (int i = 0; i < thisParameters.length; i++) {
                            Parameter thisParameter = (Parameter) thisParameters[i];
                            Parameter ancestorParameter = (Parameter) ancestorParameters[i];

                            if (!thisParameter.type.name.equals(ancestorParameter.type.name)) {
                                throw new SemanticException("El metodo heredado debe tener los mismos tipos de parametros.", thisMethod.name, thisMethod.lineNumber);
                            }
                        }
                    }
                }
            }
        }

        isConsolidated = true;
    }
}
