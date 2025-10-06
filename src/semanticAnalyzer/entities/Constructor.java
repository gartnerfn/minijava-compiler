package semanticAnalyzer.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constructor {
    String name;
    int lineNumber;
    private Map<String, Parameter> parameters = new LinkedHashMap<>();

    void isWellDeclared(){

    }
}
