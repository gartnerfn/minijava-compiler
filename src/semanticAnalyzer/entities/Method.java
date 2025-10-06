package semanticAnalyzer.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Method {
    String name;
    int lineNumber;
    String returnType;
    private Map<String, Parameter> parameters = new LinkedHashMap<>();

    void isWellDeclared(){

    }
}
