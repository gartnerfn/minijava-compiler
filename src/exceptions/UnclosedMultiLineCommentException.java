package exceptions;

public class UnclosedMultiLineCommentException extends LexicalException {
    public UnclosedMultiLineCommentException(String description, String lexeme, int lineNumber, int columnNumber, String currentLine) {
        super("Unclosed multiline comment. " + description + " missing", lexeme, lineNumber, columnNumber, currentLine);
    }
}