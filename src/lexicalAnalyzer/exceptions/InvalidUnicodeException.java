package lexicalAnalyzer.exceptions;

public class InvalidUnicodeException extends LexicalException {
    public InvalidUnicodeException(String lexeme, int lineNumber, int columnNumber, String line) {
        super("Invalid unicode character", lexeme, lineNumber, columnNumber, line);
    }
}
