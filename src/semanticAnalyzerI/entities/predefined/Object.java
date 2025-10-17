package semanticAnalyzerI.entities.predefined;

import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
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

    public void isWellDeclared() {
        addConstructor(new Constructor(new Token("classId", this.name, 0), ""));
    }
    public void consolidate(){}
    public void check(){}

    static void debugPrint(int i){
        java.lang.System.out.println(i);
    }
}
