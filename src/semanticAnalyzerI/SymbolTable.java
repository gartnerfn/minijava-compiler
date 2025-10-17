package semanticAnalyzerI;

import semanticAnalyzerI.entities.*;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.predefined.Object;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.entities.predefined.String;
import semanticAnalyzerII.nodes.sent.NodoBloque;

import java.util.HashMap;

public class SymbolTable {
    private static SymbolTable symbolTable = null;
    private final HashMap<java.lang.String, semanticAnalyzerI.entities.Class> classes = new HashMap<>();
    private final HashMap<java.lang.String, Interface> interfaces = new HashMap<>();

    public Entity currentEntity;
    public Routine currentRoutine;
    public NodoBloque currentBlock;

    private SymbolTable(){}

    public void deleteInstance(){
        symbolTable = null;
    }

    public static SymbolTable getInstance() {
        if (symbolTable == null){
            symbolTable = new SymbolTable();
            symbolTable.initializePredefined();
        }

        return symbolTable;
    }

    public void addClass(semanticAnalyzerI.entities.Class c){
        if(classes.containsKey(c.name))
            throw new SemanticException("Duplicated class.", c.name, c.lineNumber);

        classes.put(c.name, c);
    }

    public void addInterface(Interface i){
        if(interfaces.containsKey(i.name))
            throw new SemanticException("Duplicated interface.", i.name, i.lineNumber);

        interfaces.put(i.name, i);
    }

    public semanticAnalyzerI.entities.Class existsClass(java.lang.String className){
        return classes.get(className);
    }

    public Interface existsInterface(java.lang.String className){
        return interfaces.get(className);
    }

    public void initializePredefined(){
        classes.put("Object", new Object());
        classes.put("System", new semanticAnalyzerI.entities.predefined.System());
        classes.put("String", new String());
    }

    public void isWellDeclared(){
        for(Interface itf : interfaces.values())
            itf.isWellDeclared();

        for (Class cl : classes.values())
            cl.isWellDeclared();
    }

    public void consolidate(){
        for(Interface itf : interfaces.values())
            if(!itf.isConsolidated)
                itf.consolidate();

        for (Class cl : classes.values())
            if(!cl.isConsolidated)
                cl.consolidate();
    }

    public void check(){
        for(Interface itf : interfaces.values())
            itf.check();

        for (Class cl : classes.values())
            cl.check();
    }

    public void printTable() {
        System.out.println("===== TABLA DE SIMBOLOS =====");

        for (Interface itf : interfaces.values()) {
            System.out.println("Interfaz: " + itf.name);
            if (itf.ancestorInheritance!= null)
                System.out.println("  Hereda de: " + itf.ancestorInheritance);


            // --- Atributos ---
            System.out.println("  Atributos:");
            if (itf.attributes.isEmpty()) {
                java.lang.System.out.println("    (sin atributos)");
            } else {
                for (Attribute a : itf.attributes.values()) {
                    System.out.println("    " + a.type.name + " " + a.name);
                }
            }

            // --- Métodos ---
            System.out.println("  Métodos:");
            if (itf.methods.isEmpty()) {
                System.out.println("    (sin métodos)");
            } else {
                for (Method m : itf.methods.values()) {
                    java.lang.String modificador = "";
                    if(m.isAbstract)
                        modificador = "abstract ";
                    else if(m.isFinal)
                        modificador = "final ";
                    else if(m.isStatic)
                        modificador = "static ";
                    java.lang.String visibility = m.isPublic ? "public " : "private ";
                    System.out.println("    " + visibility + modificador + m.returnType.name + " " + m.name+ "()");
                    System.out.println("      Parámetros:");
                    if (m.parameters.isEmpty()) {
                        System.out.println("        (sin parámetros)");
                    } else {
                        for (Parameter p : m.parameters.values()) {
                            System.out.println("        " + p.type.name + " " + p.name);
                        }
                    }
                }
            }

            System.out.println(); // salto de línea entre clases
        }

        System.out.println("==============================");

        for (Class c : classes.values()) {
            System.out.println("Clase: " + c.name);
            if (c.ancestorInheritance!= null)
                System.out.println("  Hereda de: " + c.ancestorInheritance);
            if(c.ancestorImplementation != null)
                System.out.println("  Implementa de: " + c.ancestorImplementation);
            if(c.isAbstract)
                System.out.println("  Modificador: Abstract" );
            else if(c.isFinal)
                System.out.println("  Modificador: Final" );
            else if(c.isStatic)
                System.out.println("  Modificador: Static" );

            // --- Constructor ---
            HashMap<java.lang.String,Constructor> ctors = c.constructors;
            for (Constructor ctor : ctors.values()) {
                System.out.println("  Constructor: " + ctor.name);
                System.out.println("    Parámetros:");
                if (ctor.parameters.isEmpty()) {
                    System.out.println("      (sin parámetros)");
                } else {
                    for (Parameter p : ctor.parameters.values()) {
                        System.out.println("      " + p.type.name + " " + p.name);
                    }
                }
            }

            // --- Atributos ---
            System.out.println("  Atributos:");
            if (c.attributes.isEmpty()) {
                java.lang.System.out.println("    (sin atributos)");
            } else {
                for (Attribute a : c.attributes.values()) {
                    System.out.println("    " + a.type.name + " " + a.name);
                }
            }

            // --- Métodos ---
            System.out.println("  Métodos:");
            if (c.methods.isEmpty()) {
                System.out.println("    (sin métodos)");
            } else {
                for (Method m : c.methods.values()) {
                    java.lang.String modificador = "";
                    if(m.isAbstract)
                        modificador = "abstract ";
                    else if(m.isFinal)
                        modificador = "final ";
                    else if(m.isStatic)
                        modificador = "static ";
                    java.lang.String visibility = m.isPublic ? "public " : "private ";
                    System.out.println("    " + visibility + modificador + m.returnType.name + " " + m.name+ "()");
                    System.out.println("      Parámetros:");
                    if (m.parameters.isEmpty()) {
                        System.out.println("        (sin parámetros)");
                    } else {
                        for (Parameter p : m.parameters.values()) {
                            System.out.println("        " + p.type.name + " " + p.name);
                        }
                    }
                }
            }

            System.out.println(); // salto de línea entre clases
        }

        System.out.println("==============================");
    }
}
