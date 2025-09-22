package src;

import lexicalAnalyzer.exceptions.LexicalException;
import lexicalAnalyzer.LexicalAnalyzer;
import sourceManager.SourceManager;
import sourceManager.SourceManagerImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final String END_OF_FILE = "eof";

        SourceManager sourceManager = new SourceManagerImpl();
        String filePath = args[0];
        Token token;

        try {
            sourceManager.open(filePath);
            LexicalAnalyzer lexicalAnalyser = new LexicalAnalyzer(sourceManager);

            do{
                token = lexicalAnalyser.nextToken();
                String tokenInfo = String.format("(%s,%s,%d)", token.token(), token.lexeme(), token.lineNumber());
                System.out.println(tokenInfo);
            } while (!token.token().equals(END_OF_FILE));

            ArrayList<LexicalException> exceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : exceptions){
                final String RED = "\u001B[31m";
                final String RESET = "\u001B[0m";

                System.out.println("\n" + RED + exception.getMessage() + RESET);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }

            if(exceptions.isEmpty())
                System.out.print("\n" + "[SinErrores]");
        } catch (java.io.IOException e) {
            System.out.println("Error in file: " + e.getMessage());
        } finally {
            try {
                sourceManager.close();
            } catch (java.io.IOException e) {
                System.out.println("Error in file: " + e.getMessage());
            }
        }
    }
}