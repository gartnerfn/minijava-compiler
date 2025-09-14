package lexicalAnalyzer.exceptions;

public class UnclosedStringException extends LexicalException {
    public UnclosedStringException(String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super("Unclosed string. \" missing", lexeme, lineNumber, columnNumber, currentLine);
    }
}