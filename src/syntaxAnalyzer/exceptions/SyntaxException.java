package syntaxAnalyzer.exceptions;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String expected, String lexeme, int lineNumber) {
        super(buildMessage(expected, lexeme, lineNumber));
    }

    private static String buildMessage(String expected, String lexeme, int lineNumber) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String errorDescription =
                "Syntax error on line " + lineNumber + ". " + expected + " expected but \"" + lexeme + "\" found.\n";

        String errorMessage = "[Error:" + lexeme + "|" + lineNumber + "]";

        return RED + errorDescription + "\n\n" + errorMessage + RESET;
    }
}
