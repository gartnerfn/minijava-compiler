package src;

import lexicalAnalyzer.exceptions.LexicalException;
import lexicalAnalyzer.LexicalAnalyzer;
import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.exceptions.SemanticException;
import syntaxAnalyzer.exceptions.SyntaxException;
import syntaxAnalyzer.SyntaxAnalyzer;
import sourceManager.SourceManager;
import sourceManager.SourceManagerImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SourceManager sourceManager = new SourceManagerImpl();
        String filePath = args[0];
        SymbolTable symbolTable = SymbolTable.getInstance();
        LexicalAnalyzer lexicalAnalyser = null;

        try {
            sourceManager.open(filePath);
            lexicalAnalyser = new LexicalAnalyzer(sourceManager);
            new SyntaxAnalyzer(lexicalAnalyser);

            ArrayList<LexicalException> lexicalExceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : lexicalExceptions)
                System.out.println(exception.getMessage());

            symbolTable.isWellDeclared();
            symbolTable.consolidate();
//            symbolTable.printTable();

            symbolTable.check();

            if(lexicalExceptions.isEmpty())
                System.out.print("\n" + "[SinErrores]");

            symbolTable.deleteInstance();
        } catch (java.io.IOException e) {
            System.out.println("Error in file: " + e.getMessage());
        } catch (SyntaxException syE){
            if(lexicalAnalyser == null) return;

            ArrayList<LexicalException> lexicalExceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : lexicalExceptions)
                System.out.println(exception.getMessage());

            System.out.println(syE.getMessage());
        } catch (SemanticException smE){
            if(lexicalAnalyser == null) return;

            ArrayList<LexicalException> lexicalExceptions = lexicalAnalyser.getExceptions();

            for(LexicalException exception : lexicalExceptions)
                System.out.println(exception.getMessage());

            symbolTable.printTable();
            System.out.println(smE.getMessage());
        } finally {
            try {
                symbolTable.deleteInstance();
                sourceManager.close();
            } catch (java.io.IOException e) {
                System.out.println("Error in file: " + e.getMessage());
            }
        }
    }
}
