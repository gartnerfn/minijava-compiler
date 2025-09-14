package sintaxAnalyzer.exceptions;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String expected, String lexeme, int lineNumber) {
        super(buildMessage(expected, lexeme, lineNumber));
    }

    private static String buildMessage(String expected, String lexeme, int lineNumber) {
        String errorDescription =
                "Syntax error on line " + lineNumber + ". " + expected + " expected but \"" + lexeme + "\" found.\n";

        String errorMessage = "[Error:" + lexeme + "|" + lineNumber + "]";

        return errorDescription + "\n\n" + errorMessage;
    }
}
