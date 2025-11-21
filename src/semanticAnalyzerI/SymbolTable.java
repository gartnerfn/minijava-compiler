package semanticAnalyzerI;

import semanticAnalyzerI.entities.*;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.predefined.Object;
import semanticAnalyzerI.exceptions.SemanticException;
import semanticAnalyzerI.entities.predefined.String;
import semanticAnalyzerII.nodes.sent.NodoBloque;
import semanticAnalyzerII.nodes.sent.NodoVarLocal;

import java.util.*;

public class SymbolTable {
    private static SymbolTable symbolTable = null;
    private final HashMap<java.lang.String, semanticAnalyzerI.entities.Class> classes = new HashMap<>();
    private final HashMap<java.lang.String, Interface> interfaces = new HashMap<>();
    private final Deque<Map<java.lang.String, NodoVarLocal>> blockStack = new ArrayDeque<>();

    public Class currentClass;
    public NodoBloque currentBlock;
    public Entity currentEntity;
    public Routine currentRoutine;
    public List<java.lang.String> instructions = new ArrayList<>();
    private HashMap<java.lang.String, java.lang.String> stringLiteralLabels = new HashMap<>();
    private int stringLiteralCounter = 0;

    private SymbolTable(){
        stringLiteralLabels = new HashMap<>();
    }

    public void openBlock() {
        blockStack.push(new HashMap<>());
    }

    public void closeBlock() {
        blockStack.pop();
    }

    public void addLocalVar(NodoVarLocal localVar) {
        Map<java.lang.String, NodoVarLocal> currentBlock = blockStack.peek();

        if (currentBlock != null){
            for (Map<java.lang.String , NodoVarLocal> block : blockStack)
                if (block.containsKey(localVar.name))
                    throw new SemanticException("Variable local '" + localVar.name + "' ya declarada en este ámbito", localVar.name, localVar.lineNumber);

            if (currentRoutine != null && currentRoutine.existsParameter(localVar.name) != null)
                throw new SemanticException("Variable local '" + localVar.name + "' ya declarada como parámetro del método '" + currentRoutine.name + "'", localVar.name, localVar.lineNumber);

            if(currentRoutine != null)
                currentRoutine.addLocalVar(localVar);

            currentBlock.put(localVar.name, localVar);
        }
    }

    public Variable existsVar(java.lang.String name) {
        Variable variable = null;

        for (Map<java.lang.String , NodoVarLocal> block : blockStack) {
            NodoVarLocal var = block.get(name);
            if (var != null)
                return var;
        }

        if (currentRoutine != null)
            variable = currentRoutine.existsParameter(name);

        if (currentEntity != null && variable == null)
            variable = currentEntity.existsAttribute(name);

        return variable;
    }

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

    public Entity existsEntity(java.lang.String name){
        Class c = getClass(name);

        if(c != null)
            return c;

        return getInterface(name);
    }

    public Class getClass(java.lang.String className){
        return classes.get(className);
    }

    public Interface getInterface(java.lang.String interfaceName){
        return interfaces.get(interfaceName);
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
        addClass(new Object());
        addClass(new semanticAnalyzerI.entities.predefined.System());
        addClass(new String());
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
        currentEntity = null;
        currentRoutine = null;

        for(Interface itf : interfaces.values())
            itf.check();

        for (Class cl : classes.values())
            cl.check();
    }

    private void generateInit(){
        instructions.add(".CODE");
        instructions.add("PUSH lblMethod_main0@Init");
        instructions.add("CALL");
        instructions.add("HALT");
    }

    private void generateHeapRoutines(){
        instructions.add("simple_heap_init:");
        instructions.add("RET 0");

        instructions.add("simple_malloc:");
        instructions.add("LOADFP");
        instructions.add("LOADSP");
        instructions.add("STOREFP");
        instructions.add("LOADHL");
        instructions.add("DUP");
        instructions.add("PUSH 1");
        instructions.add("ADD");
        instructions.add("STORE 4");
        instructions.add("LOAD 3");
        instructions.add("ADD");
        instructions.add("STOREHL");
        instructions.add("STOREFP");
        instructions.add("RET 1");
    }

    public void generate(){
        currentEntity = null;
        currentClass = null;
        currentRoutine = null;

        generateInit();
        generateHeapRoutines();

        for (Class cl : classes.values())
            cl.generate();

        generateStringLiterals();
    }

    public void addInstruction(java.lang.String instruction){
        instructions.add(instruction);
    }

    public void callConstructor(Constructor constructor){
        instructions.add("PUSH lblConstructor" + constructor.parameters.size() + "@" + constructor.name);
        instructions.add("CALL");
    }

    public java.lang.String getOrCreateStringLiteralLabel(java.lang.String lexeme) {
        if(stringLiteralLabels.containsKey(lexeme))
            return stringLiteralLabels.get(lexeme);
        java.lang.String label = "str_" + stringLiteralCounter;
        stringLiteralCounter++;
        stringLiteralLabels.put(lexeme, label);
        return label;
    }

    private void generateStringLiterals() {
        if(!stringLiteralLabels.isEmpty()) {
            instructions.add(".DATA");
            for (var entry : stringLiteralLabels.entrySet()) {
                java.lang.String lexeme = entry.getKey();
                java.lang.String label = entry.getValue();

                java.lang.String cleanValue = lexeme;
                if(lexeme.startsWith("\"") && lexeme.endsWith("\"") && lexeme.length() >= 2) {
                    cleanValue = lexeme.substring(1, lexeme.length() - 1);
                }
                instructions.add(label + ": DW \"" + cleanValue + "\", 0");
            }
        }
    }

    public void callStaticMethod(Method method){
        instructions.add("PUSH lblMethod_" + method.name + method.parameters.size() + "@" + method.declaredIn.name);
        instructions.add("CALL");
    }

    public void callMethod(Method method){
        instructions.add("CALL");
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
