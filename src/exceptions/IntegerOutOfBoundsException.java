package exceptions;

public class IntegerOutOfBoundsException extends LexicalException {
    public IntegerOutOfBoundsException(String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super("Integer has to have less than 10 digits", lexeme, lineNumber, columnNumber, currentLine);
    }
}
