package semanticAnalyzer.entities.predefined;

import semanticAnalyzer.entities.Class;

public class String extends Class{
    public String(){
        super(new src.Token("classId", "String", 0));
        isConsolidated = true;
    }

    public void isWellDeclared() {}
    public void consolidate(){}
}
