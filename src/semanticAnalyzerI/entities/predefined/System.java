package semanticAnalyzerI.entities.predefined;
import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
import semanticAnalyzerI.entities.Method;
import semanticAnalyzerI.entities.Parameter;
import semanticAnalyzerI.types.*;
import semanticAnalyzerII.nodes.sent.NodoBloqueNulo;
import src.Token;

public class System extends Class {
    SymbolTable symbolTable = SymbolTable.getInstance();

    public System(){
        super(new Token("classId", "System", 0));

        createConstructor();

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

    public void generate(){
        symbolTable.addInstruction.add(".DATA");
        symbolTable.addInstruction.add("lblVT_" + this.name + ": NOP");

        symbolTable.addInstruction.add(".CODE");

        for (Constructor constructor : constructors.values())
            constructor.generate();

        symbolTable.addInstruction("lblMethod_read0@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("READ");
        symbolTable.addInstruction("STORE 3");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 0");

        symbolTable.addInstruction("lblMethod_printB1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("BPRINT");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printC1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("CPRINT");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printI1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("IPRINT");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printS1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("SPRINT");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_println0@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("PRNLN");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 0");

        symbolTable.addInstruction("lblMethod_printBln1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("BPRINT");
        symbolTable.addInstruction("PRNLN");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printCln1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("CPRINT");
        symbolTable.addInstruction("PRNLN");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printIln1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("IPRINT");
        symbolTable.addInstruction("PRNLN");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");

        symbolTable.addInstruction("lblMethod_printSln1@System:");
        symbolTable.addInstruction("LOADFP");
        symbolTable.addInstruction("LOADSP");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("LOAD 3");
        symbolTable.addInstruction("SPRINT");
        symbolTable.addInstruction("PRNLN");
        symbolTable.addInstruction("STOREFP");
        symbolTable.addInstruction("RET 1");
    }
}
