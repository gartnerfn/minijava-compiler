package semanticAnalyzerII.nodes.sent;

import semanticAnalyzerI.exceptions.SemanticException;

import java.util.ArrayList;
import java.util.List;

public class NodoBloque extends NodoSentencia{
    public List<NodoSentencia> sentences = new ArrayList<>();

    public String getName(){
        return name;
    }
    public int getLineNumber(){
        return lineNumber;
    }

    public void check(){
        symbolTable.openBlock();

        boolean returnFound = false;

        for(NodoSentencia sentence : sentences){
            if(returnFound && !(sentence instanceof NodoSentenciaVacia))
                throw new SemanticException("Código inalcanzable después de una sentencia de retorno", sentence.getName(), sentence.getLineNumber());

            sentence.check();

            if(sentence.guaranteeReturn())
                returnFound = true;
        }

        symbolTable.closeBlock();
    }

    public boolean guaranteeReturn(){
        for(NodoSentencia sentence : sentences){
            if(sentence.guaranteeReturn())
                return true;
        }

        return false;
    }

    public void generate(){
        for(NodoSentencia sentence : sentences)
            sentence.generate();

        //TODO
        symbolTable.addInstruction("FMEM 0");
    }
}
