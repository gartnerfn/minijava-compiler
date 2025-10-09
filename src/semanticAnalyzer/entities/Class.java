package semanticAnalyzer.entities;

import semanticAnalyzer.exceptions.SemanticException;
import src.Token;

import java.util.HashMap;

public class Class extends Entity{
    String ancestorImplementation;

    public boolean isFinal;
    public boolean isStatic;

    protected HashMap<String, Constructor> constructors = new HashMap<>();

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
        ancestorInheritance = ancestor.lexeme();
    }

    public void implementsFrom(Token ancestor){
        if(ancestor != null)
            ancestorImplementation = ancestor.lexeme();
        else
            ancestorImplementation = null;
    }

    public void addMethod(Method method){
        if(methods.containsKey(method.name))
            throw new SemanticException("Duplicated method.", method.name, method.lineNumber);

        methods.put(method.name, method);
    }

    public void addConstructor(Constructor constructor){
        if(constructors.containsKey(constructor.name))
            throw new SemanticException("Duplicated constructor.", constructor.name, constructor.lineNumber);

        if(isAbstract)
            throw new SemanticException("Abstract classes cant have a constructor.", constructor.name, constructor.lineNumber);

        constructors.put(constructor.name, constructor);
    }

    public boolean isConcrete(){
        return !isAbstract;
    }

    public boolean hasConstructor(){
        return !constructors.isEmpty();
    }

    public Constructor existsConstructor(String constructorName){
        return constructors.get(constructorName);
    }

    public void isWellDeclared(){
        if(isConcrete() && !hasConstructor())
            addConstructor(new Constructor(new Token("classId", this.name, 0), ""));

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

        while(ancestorInheritanceClass.ancestorInheritance != null ){
            if( ancestorInheritanceClass.ancestorInheritance.equals(name))
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

        if(ancestorInheritanceClass != null) {
            if(!ancestorInheritanceClass.isConsolidated)
                ancestorInheritanceClass.consolidate();

            for (Attribute attribute : ancestorInheritanceClass.attributes.values()) {
                if (!this.attributes.containsKey(attribute.name)){
                    this.attributes.put(attribute.name, attribute);
                }
            }

            for (Method method : ancestorInheritanceClass.methods.values()) {
                Method thisMethod = this.methods.get(method.name);

                if (thisMethod == null) {
                    if(method.isAbstract)
                        throw new SemanticException("La clase no implementa todos los metodos heredados.", method.name, method.lineNumber);
                    else
                        this.methods.put(method.name + ancestorInheritance, method);
                } else {
                    if(method.isFinal){
                        throw new SemanticException("El metodo heredado es final y no puede ser redefinido.", thisMethod.name, thisMethod.lineNumber);
                    }else if(method.isStatic){
                        throw new SemanticException("El metodo heredado es static y no puede ser redefinido.", thisMethod.name, thisMethod.lineNumber);
                    } else if (!thisMethod.returnType.name.equals(method.returnType.name)) {
                        throw new SemanticException("El metodo heredado debe tener el mismo tipo de retorno.", thisMethod.name, thisMethod.lineNumber);
                    } else if (thisMethod.parameters.size() != method.parameters.size()) {
                        throw new SemanticException("El metodo heredado debe tener la misma cantidad de parametros.", thisMethod.name, thisMethod.lineNumber);
                    } else if (method.isPublic && !thisMethod.isPublic){
                        throw new SemanticException("El metodo redefinido no puede reducir la visibilidad del metodo heredado.", thisMethod.name, thisMethod.lineNumber);
                    } else if(thisMethod.isStatic){
                        throw new SemanticException("No se puede redefinir un metodo no estatico convirtiendolo en estatico.", thisMethod.name, thisMethod.lineNumber);
                    }
                    else {
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

        if(ancestorImplementation != null){
            Interface ancestorImplementationInterface = symbolTable.existsInterface(ancestorImplementation);

            if(ancestorImplementationInterface != null){
                if(!ancestorImplementationInterface.isConsolidated)
                    ancestorImplementationInterface.consolidate();

                for(Attribute attribute : ancestorImplementationInterface.attributes.values()){
                    if(!this.attributes.containsKey(attribute.name))
                        this.attributes.put(attribute.name, attribute);
                }

                for(Method method : ancestorImplementationInterface.methods.values()){
                    if(!this.methods.containsKey(method.name))
                        throw new SemanticException("La clase no implementa todos los metodos de la interfaz.", method.name, method.lineNumber);
                }
            }
        }

        isConsolidated = true;
    }
}
