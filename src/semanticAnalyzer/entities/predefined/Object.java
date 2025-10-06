package semanticAnalyzer.entities.predefined;

import semanticAnalyzer.entities.Class;
import src.Token;

public class Object extends Class {

    public Object(Token tkn){
        super(tkn);
    }

    static void debugPrint(int i){
        java.lang.System.out.println(i);
    }
}
