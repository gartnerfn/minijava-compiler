package semanticAnalyzer.entities.predefined;
import semanticAnalyzer.entities.Class;
import src.Token;

import java.io.IOException;

public class System extends Class {
    public System(Token tkn){
        super(tkn);
    }

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
