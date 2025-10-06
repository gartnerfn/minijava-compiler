package semanticAnalyzer.entities;

import semanticAnalyzer.entities.predefined.Object;
import semanticAnalyzer.exceptions.SemanticException;
import src.Token;

import java.util.HashMap;

public class SymbolTable {
//    String inheritance; ?
    private static SymbolTable symbolTable = null;
    private final HashMap<String, Class> classes = new HashMap<>();

    public Method currentMethod;
    public Class currentClass;

    private SymbolTable(){};

    public static void deleteInstance(){
        symbolTable = null;
    }

    public static SymbolTable getInstance() {
        if (symbolTable == null){
            symbolTable = new SymbolTable();
            symbolTable.initializePredefined();
        }

        return symbolTable;
    }

    public void addClass(Class c){
        if(classes.containsKey(c.name))
            throw new SemanticException("Duplicated class.", c.name, c.lineNumber);

        classes.put(c.name, c);
    }

    Class existsClass(String className){
        return classes.get(className);
    }

    public void initializePredefined(){
        classes.put("Object", new Object(new Token("classId", "Object", 0)));
    }

    public void isWellDeclared(){
        for (Class cl : classes.values())
            cl.isWellDeclared();
    }

}
