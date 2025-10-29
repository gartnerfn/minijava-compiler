package semanticAnalyzerII.nodes.sent;

public class NodoSentenciaVacia extends NodoSentencia{
    public void check(){}

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public boolean guaranteeReturn(){
        return false;
    }
}
