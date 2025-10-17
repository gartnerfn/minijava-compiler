package semanticAnalyzerII.nodes.sent;


import java.util.ArrayList;
import java.util.List;

public class NodoBloque extends NodoSentencia{
    public List<NodoSentencia> sentences = new ArrayList<>();

    public void check(){
        for(NodoSentencia sentence : sentences)
            sentence.check();
    }
}
