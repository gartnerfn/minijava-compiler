package semanticAnalyzerI.entities.predefined;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.types.IntType;
import semanticAnalyzerI.types.VoidType;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import src.Token;

public class Object extends Class {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public Object(){
        super(new Token("classId", "Object", 0));
        this.ancestorInheritance = null;

        createConstructor();

        Method debugPrint = new Method(new Token("methodVarId", "debugPrint", 0), new VoidType(), "static", "", this);
        debugPrint.addBlock(new NodoBloqueNulo());
        debugPrint.addParameter(new Parameter(new Token("methodVarId","i",0), new IntType()));

        addMethod(debugPrint);
    }

    public boolean isConcrete(){
        return false;
    }

    public void isWellDeclared() {}
    public void consolidate(){
        calculateOffsets(null);
        isConsolidated = true;
    }
    public void check(){}

    public void generate(){
        symbolTable.addInstruction(".DATA");
        symbolTable.addInstruction("lblVT_" + this.name + ": DW 0");

        symbolTable.addInstruction(".CODE");

        for (Constructor constructor : constructors.values())
            constructor.generate();

        symbolTable.addInstruction("lblMethod_debugPrint1@Object:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("IPRINT");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");
    }
}
