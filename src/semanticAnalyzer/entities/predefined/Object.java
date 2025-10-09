package semanticAnalyzer.entities.predefined;

import semanticAnalyzer.entities.Class;
import src.Token;

public class Object extends Class {

    public Object(){
        super(new Token("classId", "Object", 0));
        this.ancestorInheritance = null;
        isConsolidated = true;
    }

    public boolean isConcrete(){
        return false;
    }

    public void isWellDeclared() {}
    public void consolidate(){}

    static void debugPrint(int i){
        java.lang.System.out.println(i);
    }
}
