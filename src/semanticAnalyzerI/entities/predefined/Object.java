package semanticAnalyzerI.entities.predefined;

import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.types.IntType;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import src.Token;

public class Object extends Class {

    public Object(){
        super(new Token("classId", "Object", 0));
        this.ancestorInheritance = null;
        isConsolidated = true;

        addConstructor(new Constructor(new Token("classId", this.name, 0), ""));

        Method debugPrint = new Method(new Token("methodVarId", "debugPrint", 0), new VoidType(), "static", "", this);
        debugPrint.addBlock(new NodoBloqueNulo());
        debugPrint.addParameter(new Parameter(new Token("methodVarId","i",0), new IntType()));

        addMethod(debugPrint);
    }

    public boolean isConcrete(){
        return false;
    }

    public void isWellDeclared() {}
    public void consolidate(){}
    public void check(){}

    static void debugPrint(int i){
        java.lang.System.out.println(i);
    }
}
