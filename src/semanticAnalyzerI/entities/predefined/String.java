package semanticAnalyzerI.entities.predefined;

import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.entities.Constructor;
import src.Token;

public class String extends Class{
    public String(){
        super(new src.Token("classId", "String", 0));

        addConstructor(new Constructor(new Token("classId", this.name, 0), ""));
    }

    public void isWellDeclared() {}
    public void check(){}
}
