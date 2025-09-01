package exceptions;

public class InvalidadOctalNumberException extends LexicalException {
    public InvalidadOctalNumberException(String lexeme, int lineNumber, int columnNumber, String line) {
        super("Invalid octal number", lexeme, lineNumber, columnNumber, line);
    }
}
