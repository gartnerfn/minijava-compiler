package exceptions;

public class UnclosedCharException extends LexicalException {
    public UnclosedCharException(String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super("Unclosed char. ' missing", lexeme, lineNumber, columnNumber + 1, currentLine);
    }
}
