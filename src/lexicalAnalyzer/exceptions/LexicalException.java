package lexicalAnalyzer.exceptions;

public class LexicalException extends RuntimeException {
    private final String messageWithCode;
    private final String messageWithoutCode;

    public LexicalException(String description, String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super();
        this.messageWithCode = buildMessage(description, lexeme, lineNumber, columnNumber, currentLine, true);
        this.messageWithoutCode = buildMessage(description, lexeme, lineNumber, columnNumber, currentLine, false);
    }

    private String buildMessage(String description, String lexeme, int lineNumber, int columnNumber, String currentLine, boolean showErrorCode) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String lineNumberStr = Integer.toString(lineNumber);

        String errorDescription = description
                + " on line " + lineNumber
                + ", column " + columnNumber
                + ": \n" + lexeme + "\nis not valid\n";

        String errorDetail = "Detail: \n" + lineNumberStr + ". " + currentLine;

        String errorPointer = " ".repeat(lineNumberStr.length() + 2 + columnNumber - 1) + "^";

        String errorCode = showErrorCode ? "\n\n[Error:" + lexeme + "|" + lineNumber + "]" : "";

        return RED + errorDescription + "\n" + errorDetail + "\n" + errorPointer + errorCode + RESET + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    public String getMessage(boolean showErrorCode) {
        return showErrorCode ? messageWithCode : messageWithoutCode;
    }

    @Override
    public String getMessage() {
        return messageWithCode;
    }
}
