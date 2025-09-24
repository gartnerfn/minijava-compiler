package src;

import lexicalAnalyzer.exceptions.LexicalException;
import lexicalAnalyzer.LexicalAnalyzer;
import sintaxAnalyzer.exceptions.SyntaxException;
import sintaxAnalyzer.SyntaxAnalyzer;
import sourceManager.SourceManager;
import sourceManager.SourceManagerImpl;

import java.util.ArrayList;

public class MainSint {
    public static void main(String[] args) {
        SourceManager sourceManager = new SourceManagerImpl();
        String filePath = args[0];
//        String filePath = "resources/sinErrores/sintCorrecto39.java";
        LexicalAnalyzer lexicalAnalyser = null;

        try {
            sourceManager.open(filePath);
            lexicalAnalyser = new LexicalAnalyzer(sourceManager);
            new SyntaxAnalyzer(lexicalAnalyser);

            ArrayList<LexicalException> lexicalExceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : lexicalExceptions)
                System.out.println(exception.getMessage(false));

            System.out.print("\n" + "[SinErrores]");
        } catch (java.io.IOException e) {
            System.out.println("Error in file: " + e.getMessage());
        } catch (SyntaxException se){
            if(lexicalAnalyser == null) return;

            ArrayList<LexicalException> lexicalExceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : lexicalExceptions)
                System.out.println(exception.getMessage(false));

            System.out.println(se.getMessage());
        } finally {
            try {
                sourceManager.close();
            } catch (java.io.IOException e) {
                System.out.println("Error in file: " + e.getMessage());
            }
        }
    }
}
