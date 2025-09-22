package lexicalAnalyzer.exceptions;

public class LexicalException extends RuntimeException {
    public LexicalException(String description, String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super(buildMessage(description,lexeme,lineNumber,columnNumber, currentLine));
    }

    private static String buildMessage(String description, String lexeme, int lineNumber, int columnNumber, String currentLine) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String lineNumberStr = Integer.toString(lineNumber);

        String errorDescription = description
                + " on line " + lineNumber
                + ", column " + columnNumber
                + ": \n" + lexeme + "\nis not valid\n";

        String errorDetail = "Detail: \n" + lineNumberStr + ". " + currentLine;

        String errorPointer = " ".repeat(lineNumberStr.length() + 2 + columnNumber - 1) + "^";

        String errorMessage = "[Error:" + lexeme + "|" + lineNumber + "]";

        return RED + errorDescription + "\n" + errorDetail + "\n" + errorPointer + "\n\n" + errorMessage + RESET + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
