import exceptions.LexicalException;
import lexicalAnalyser.LexicalAnalyser;
import lexicalAnalyser.Token;
import sourceManager.SourceManager;
import sourceManager.SourceManagerImplOrig;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final String END_OF_FILE = "EOF";

        SourceManager sourceManager = new SourceManagerImplOrig();
//        String filePath = args[0];
        String filePath = "resources/conErrores/lexConErrores22.java";
        Token token;

        try {
            sourceManager.open(filePath);
            LexicalAnalyser lexicalAnalyser = new LexicalAnalyser(sourceManager);
            token = lexicalAnalyser.nextToken();

            while (!token.token().equals(END_OF_FILE)) {
                String tokenInfo = String.format("(%s,%s,%d)", token.token(), token.lexeme(), token.lineNumber());
                System.out.println(tokenInfo);
                token = lexicalAnalyser.nextToken();
            }

            ArrayList<LexicalException> exceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : exceptions){
                final String RED = "\u001B[31m";
                final String RESET = "\u001B[0m";

                System.out.println("\n" + RED + exception.getMessage() + RESET);
                System.out.println("------------------------------------------");
            }

            if(exceptions.isEmpty())
                System.out.print("\n" + "[SinErrores]");
        } catch (java.io.IOException e) {
            System.out.println("Error in file: " + e.getMessage());
        } finally {
            try {
                sourceManager.close();
            } catch (java.io.IOException e) {
                System.out.println("An error with the file happened: " + e.getMessage());
            }
        }
    }
}