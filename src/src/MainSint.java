package src;

import lexicalAnalyzer.lexicalAnalyzer;
import sintaxAnalyzer.exceptions.SyntaxException;
import sintaxAnalyzer.syntaxAnalyzer;
import sourceManager.SourceManager;
import sourceManager.SourceManagerImpl;

public class MainSint {
    public static void main(String[] args) {
        SourceManager sourceManager = new SourceManagerImpl();
        String filePath = args[0];
//        String filePath = "resources/sinErrores/sintCorrecto06.java";

        try {
            sourceManager.open(filePath);
            lexicalAnalyzer lexicalAnalyser = new lexicalAnalyzer(sourceManager);
            syntaxAnalyzer syntaxAnalyzer = new syntaxAnalyzer(lexicalAnalyser);

            System.out.print("\n" + "[SinErrores]");
        } catch (java.io.IOException e) {
            System.out.println("Error in file: " + e.getMessage());
        } catch (SyntaxException se){
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
