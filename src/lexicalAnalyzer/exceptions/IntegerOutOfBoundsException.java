package lexicalAnalyzer.exceptions;

public class IntegerOutOfBoundsException extends LexicalException {
    public IntegerOutOfBoundsException(String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super("Integer must have less than 10 digits", lexeme, lineNumber, columnNumber, currentLine);
    }
}
