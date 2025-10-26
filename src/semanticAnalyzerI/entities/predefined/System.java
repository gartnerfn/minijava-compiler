package semanticAnalyzerI.entities.predefined;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.types.*;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import src.Token;

import java.io.IOException;

public class System extends Class {
    public System(){
        super(new Token("classId", "System", 0));

        addConstructor(new Constructor(new Token("classId", this.name, 0), ""));

        Method read = new Method(new Token("methodVarId", "read", 0), new IntType(), "static", "", this);
        read.addBlock(new NodoBloqueNulo());
        addMethod(read);

        Method printB = new Method(new Token("methodVarId", "printB", 0), new VoidType(), "static", "", this);
        printB.addBlock(new NodoBloqueNulo());
        printB.addParameter(new Parameter(new Token("methodVarId","b",0), new BooleanType()));
        addMethod(printB);

        Method printC = new Method(new Token("methodVarId", "printC", 0), new VoidType(), "static", "", this);
        printC.addBlock(new NodoBloqueNulo());
        printC.addParameter(new Parameter(new Token("methodVarId","c",0), new CharType()));
        addMethod(printC);

        Method printI = new Method(new Token("methodVarId", "printI", 0), new VoidType(), "static", "", this);
        printI.addBlock(new NodoBloqueNulo());
        printI.addParameter(new Parameter(new Token("methodVarId","i",0), new IntType()));
        addMethod(printI);

        Method printS = new Method(new Token("methodVarId", "printS", 0), new VoidType(), "static", "", this);
        printS.addBlock(new NodoBloqueNulo());
        printS.addParameter(new Parameter(new Token("methodVarId","s",0), new ReferenceType(new Token("classId","String",0))));
        addMethod(printS);

        Method println = new Method(new Token("methodVarId", "println", 0), new VoidType(), "static", "", this);
        println.addBlock(new NodoBloqueNulo());
        addMethod(println);

        Method printBln = new Method(new Token("methodVarId", "printBln", 0), new VoidType(), "static", "", this);
        printBln.addBlock(new NodoBloqueNulo());
        printBln.addParameter(new Parameter(new Token("methodVarId","b",0), new BooleanType()));
        addMethod(printBln);

        Method printCln = new Method(new Token("methodVarId", "printCln", 0), new VoidType(), "static", "", this);
        printCln.addBlock(new NodoBloqueNulo());
        printCln.addParameter(new Parameter(new Token("methodVarId","c",0), new CharType()));
        addMethod(printCln);

        Method printIln = new Method(new Token("methodVarId", "printIln", 0), new VoidType(), "static", "", this);
        printIln.addBlock(new NodoBloqueNulo());
        printIln.addParameter(new Parameter(new Token("methodVarId","i",0), new IntType()));
        addMethod(printIln);

        Method printSln = new Method(new Token("methodVarId", "printSln", 0), new VoidType(), "static", "", this);
        printSln.addBlock(new NodoBloqueNulo());
        printSln.addParameter(new Parameter(new Token("methodVarId","s",0), new ReferenceType(new Token("classId","String",0))));
        addMethod(printSln);
    }

    public void isWellDeclared() {}
    public void check(){}

    public static int read() {
        try {
            return java.lang.System.in.read();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer de la entrada estándar", e);
        }
    }

    public static void printB(boolean b) {
        java.lang.System.out.print(b);
    }

    public static void printC(char c) {
        java.lang.System.out.print(c);
    }

    public static void printI(int i) {
        java.lang.System.out.print(i);
    }

    public static void printS(String s) {
        java.lang.System.out.print(s);
    }

    public static void println() {
        java.lang.System.out.println();
    }

    public static void printBln(boolean b) {
        java.lang.System.out.println(b);
    }

    public static void printCln(char c) {
        java.lang.System.out.println(c);
    }

    public static void printIln(int i) {
        java.lang.System.out.println(i);
    }

    public static void printSln(String s) {
        java.lang.System.out.println(s);
    }
}
