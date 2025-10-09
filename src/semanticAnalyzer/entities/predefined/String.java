package semanticAnalyzer.entities.predefined;

import semanticAnalyzer.entities.Class;
import semanticAnalyzer.entities.Constructor;
import src.Token;

public class String extends Class{
    public String(){
        super(new src.Token("classId", "String", 0));
        isConsolidated = true;
    }

    public void isWellDeclared() {
        addConstructor(new Constructor(new Token("classId", this.name, 0), ""));
    }
    public void consolidate(){}
}
