package semanticAnalyzerI.exceptions;

public class SemanticException extends RuntimeException {
    public SemanticException(String error, String lexeme, int lineNumber) {
        super(buildMessage(error, lexeme, lineNumber));
    }

    private static String buildMessage(String error, String lexeme, int lineNumber) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String errorDescription =
                "Semantic error on line " + lineNumber + ". " + error;

        String errorMessage = "[Error:" + lexeme + "|" + lineNumber + "]";

        return RED + errorDescription + "\n\n" + errorMessage + RESET;
    }
}
