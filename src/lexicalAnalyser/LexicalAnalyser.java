package lexicalAnalyser;

import exceptions.*;
import sourceManager.SourceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyser {
    private final SourceManager sourceManager;
    private String lexeme;
    private char currentCharacter;
    private final Map<String, String> reservedWords = new HashMap<>();
    private final ArrayList<LexicalException> exceptions = new ArrayList<>();

    String[] words = {
        "class", "extends", "public", "static", "void",
        "boolean", "char", "int", "abstract", "final",
        "if", "else", "while", "return", "var",
        "this", "new", "null", "true", "false"
    };

    public LexicalAnalyser(SourceManager sourceManager) {
        this.sourceManager = sourceManager;

        for (String word : words)
            reservedWords.put(word, "rw_" + word);

        updateCurrentCharacter();
    }

    public Token nextToken(){
        lexeme = "";
        return initial();
    }

    public ArrayList<LexicalException> getExceptions(){
        return exceptions;
    }

    private void updateLexeme(){
        lexeme += currentCharacter;
    }

    private void updateCurrentCharacter(){
        try {
            currentCharacter = sourceManager.getNextChar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCurrentLine(){
        return sourceManager.getCurrentLine();
    }

    private int getLineNumber(){
        return sourceManager.getLineNumber();
    }

    private int getColumnNumber(){
        return sourceManager.getColumnNumber();
    }

    private boolean is(char c){
        return currentCharacter == c;
    }

    private boolean isUppercase(){
        return Character.isUpperCase(currentCharacter);
    }

    private boolean isLowercase(){
        return !isUppercase();
    }

    private boolean isLetter(){
        return Character.isLetter(currentCharacter);
    }

    private boolean isDigit(){
        return Character.isDigit(currentCharacter);
    }

    private boolean isWhitespace(){
        return Character.isWhitespace(currentCharacter);
    }

    private boolean isEOF(){
        return currentCharacter == SourceManager.END_OF_FILE;
    }

    private Token initial(){
        if(isLetter() && isUppercase()) {
            updateLexeme();
            updateCurrentCharacter();
            return classId();
        }else if(isLetter() && isLowercase()){
            updateLexeme();
            updateCurrentCharacter();
            return methodVarId();
        } else if(isDigit()){
            updateLexeme();
            updateCurrentCharacter();
            return intLiteral();
        } else if(is('\'')){
            updateLexeme();
            updateCurrentCharacter();
            return charLiteralInitial();
        } else if(is( '"')){
            updateLexeme();
            updateCurrentCharacter();
            return stringLiteralInitial();
        } else if(is('>')){
            updateLexeme();
            updateCurrentCharacter();
            return greaterThan();
        } else if(is('<')){
            updateLexeme();
            updateCurrentCharacter();
            return lessThan();
        } else if(is('!')){
            updateLexeme();
            updateCurrentCharacter();
            return not();
        } else if(is('=')){
            updateLexeme();
            updateCurrentCharacter();
            return assignment();
        } else if(is('&')){
            updateLexeme();
            updateCurrentCharacter();
            return ampersand();
        } else if(is('|')){
            updateLexeme();
            updateCurrentCharacter();
            return verticalBar();
        } else if(is('%')){
            updateLexeme();
            updateCurrentCharacter();
            return mod();
        } else if(is('+')){
            updateLexeme();
            updateCurrentCharacter();
            return addition();
        } else if(is('-')){
            updateLexeme();
            updateCurrentCharacter();
            return substraction();
        } else if(is('*')){
            updateLexeme();
            updateCurrentCharacter();
            return multiplication();
        } else if(is('/')) {
            updateLexeme();
            updateCurrentCharacter();
            return division();
        } else if(is('(')) {
            updateLexeme();
            updateCurrentCharacter();
            return openingParenthesis();
        } else if(is(')')) {
            updateLexeme();
            updateCurrentCharacter();
            return closingParenthesis();
        } else if(is('{')) {
            updateLexeme();
            updateCurrentCharacter();
            return openingBrace();
        } else if(is('}')) {
            updateLexeme();
            updateCurrentCharacter();
            return closingBrace();
        } else if(is(';')) {
            updateLexeme();
            updateCurrentCharacter();
            return semicolon();
        } else if(is(',')) {
            updateLexeme();
            updateCurrentCharacter();
            return comma();
        } else if(is('.')) {
            updateLexeme();
            updateCurrentCharacter();
            return dot();
        } else if(is(':')) {
            updateLexeme();
            updateCurrentCharacter();
            return colon();
        } else if(isWhitespace()){
            updateCurrentCharacter();
            return initial();
        } else if(is(SourceManager.END_OF_FILE)){
            return EOF();
        }

        updateLexeme();
        exceptions.add(new LexicalException( "Invalid character", lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
        updateCurrentCharacter();
        return nextToken();
    }


    private Token classId(){
        if(isLetter() || isDigit() || is('_')){
            updateLexeme();
            updateCurrentCharacter();
            return classId();
        }

        return new Token("classId" , lexeme, getLineNumber());
    }


    private Token methodVarId(){
        if(isLetter() || isDigit() || is('_')){
            updateLexeme();
            updateCurrentCharacter();
            return methodVarId();
        }

        if(reservedWords.containsKey(lexeme))
            return new Token(reservedWords.get(lexeme), lexeme, getLineNumber());

        return new Token("methodVarId" , lexeme, getLineNumber());
    }


    private Token intLiteral(){
        if(isDigit() && lexeme.length() == 9) {
            updateLexeme();
            exceptions.add(new IntegerOutOfBoundsException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(isDigit()){
            updateLexeme();
            updateCurrentCharacter();
            return intLiteral();
        }

        return new Token("intLiteral" , lexeme, getLineNumber());
    }


    private Token charLiteralInitial(){
        if(isEOF() || isWhitespace() && !is(' ')){
            exceptions.add(new UnclosedCharException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('\\')){
            updateLexeme();
            updateCurrentCharacter();
            return charLiteral3();
        }

        updateLexeme();
        updateCurrentCharacter();
        return charLiteral2();
    }
    private Token charLiteral2(){
        if(is('\'')){
            updateLexeme();
            updateCurrentCharacter();
            return charLiteralFinal();
        }

        exceptions.add(new UnclosedCharException(lexeme, getLineNumber(), getColumnNumber() - 1, getCurrentLine()));
        return nextToken();
    }
    private Token charLiteral3(){
        if(isEOF() || isWhitespace() && !is(' ')){
            exceptions.add(new UnclosedCharException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('u')){
            updateLexeme();
            updateCurrentCharacter();
            return unicodeChar(lexeme.length());
        }

        updateLexeme();
        updateCurrentCharacter();
        return charLiteral2();
    }
    private Token unicodeChar(int initialLength){
        if(is('\'') && lexeme.length() - initialLength == 4){
            updateLexeme();
            updateCurrentCharacter();
            return charLiteralFinal();
        }

        if(lexeme.length() - initialLength == 4){
            int columnNumber = isWhitespace() && !is(' ') ? getColumnNumber() : getColumnNumber() - 1;

            exceptions.add(new UnclosedCharException(lexeme, getLineNumber(), columnNumber, getCurrentLine()));
            return nextToken();
        }

        if(isLetter() || isDigit()){
            updateLexeme();
            updateCurrentCharacter();
            return unicodeChar(initialLength);
        }

        exceptions.add(new InvalidUnicodeException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
        return nextToken();
    }
    private Token charLiteralFinal(){
        return new Token("charLiteral" , lexeme, getLineNumber());
    }


    private Token stringLiteralInitial(){
        if(isEOF() || isWhitespace() && !is(' ')){
            exceptions.add(new UnclosedStringException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('\\')){
            updateLexeme();
            updateCurrentCharacter();
            return stringLiteral2();
        }

        if(is('"')){
            updateLexeme();
            updateCurrentCharacter();
            return stringLiteralFinal();
        }

        updateLexeme();
        updateCurrentCharacter();
        return stringLiteralInitial();
    }
    private Token stringLiteral2(){
        if(isEOF() || isWhitespace() && !is(' ')){
            exceptions.add(new UnclosedStringException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('u')){
            updateLexeme();
            updateCurrentCharacter();
            return unicodeString(lexeme.length());
        }

        updateLexeme();
        updateCurrentCharacter();
        return stringLiteralInitial();
    }
    private Token unicodeString(int initialLength) {
        if(lexeme.length() - initialLength == 4)
            return stringLiteralInitial();

        if(isLetter() || isDigit()){
            updateLexeme();
            updateCurrentCharacter();
            return unicodeString(initialLength);
        }

        exceptions.add(new InvalidUnicodeException(lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
//        return nextToken();
        return stringLiteralInitial();
    }
    private Token stringLiteralFinal(){
        return new Token("stringLiteral", lexeme, getLineNumber());
    }


    private Token greaterThan(){
        if(is('=')){
            updateLexeme();
            updateCurrentCharacter();
            return operatorPlusEquals("greaterThanOrEquals");
        }

        return new Token("greaterThan" , lexeme, getLineNumber());
    }


    private Token lessThan(){
        if(is('=')){
            updateLexeme();
            updateCurrentCharacter();
            return operatorPlusEquals("lessThanOrEquals");
        }

        return new Token("lessThan" , lexeme, getLineNumber());
    }


    private Token not(){
        if(is('=')){
            updateLexeme();
            updateCurrentCharacter();
            return operatorPlusEquals("notEquals");
        }

        return new Token("not" , lexeme, getLineNumber());
    }


    private Token assignment(){
        if(is('=')){
            updateLexeme();
            updateCurrentCharacter();
            return operatorPlusEquals("equals");
        }

        return new Token("assignment" , lexeme, getLineNumber());
    }


    private Token operatorPlusEquals(String token){
        return new Token(token, lexeme, getLineNumber());
    }


    private Token ampersand(){
        if(is('&')){
            updateLexeme();
            updateCurrentCharacter();
            return and();
        }

        exceptions.add(new LexicalException("Single '&' is not allowed, use '&&' instead for AND operation", lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
        return nextToken();
    }
    private Token and(){
        return new Token("and" , lexeme, getLineNumber());
    }


    private Token verticalBar(){
        if(is('|')){
            updateLexeme();
            updateCurrentCharacter();
            return or();
        }

        exceptions.add(new LexicalException("Single '|' is not allowed, use '||' instead for OR operation", lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
        return nextToken();
    }
    private Token or(){
        return new Token("or" , lexeme, getLineNumber());
    }


    private Token mod(){
        return new Token("mod" , lexeme, getLineNumber());
    }


    private Token addition(){
        if(is('+')){
            updateLexeme();
            updateCurrentCharacter();
            return increment();
        }

        return new Token("addition" , lexeme, getLineNumber());
    }
    private Token increment(){
        return new Token("increment" , lexeme, getLineNumber());
    }


    private Token substraction() {
        if(is('-')){
            updateLexeme();
            updateCurrentCharacter();
            return decrement();
        }

        return new Token("substraction" , lexeme, getLineNumber());
    }
    private Token decrement(){
        return new Token("decrement" , lexeme, getLineNumber());
    }


    private Token multiplication(){
        return new Token("multiplication" , lexeme, getLineNumber());
    }


    private Token division(){
        if(is('/')){
            updateLexeme();
            updateCurrentCharacter();
            return singleLineComment();
        }

        if(is('*')){
            updateLexeme();
            updateCurrentCharacter();
            return multiLineCommentInitial();
        }

        return new Token("division" , lexeme, getLineNumber());
    }

    private Token singleLineComment(){
        if(is('\n') || is(SourceManager.END_OF_FILE))
            return nextToken();

        updateLexeme();
        updateCurrentCharacter();
        return singleLineComment();
    }

    private Token multiLineCommentInitial(){
        if(is(SourceManager.END_OF_FILE)) {
            exceptions.add(new UnclosedMultiLineCommentException("*/", lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('*')){
            updateLexeme();
            updateCurrentCharacter();
            return multiLineComment2();
        }

        updateLexeme();
        updateCurrentCharacter();
        return multiLineCommentInitial();
    }
    private Token multiLineComment2(){
        if(is(SourceManager.END_OF_FILE)) {
            exceptions.add(new UnclosedMultiLineCommentException("/", lexeme, getLineNumber(), getColumnNumber(), getCurrentLine()));
            updateCurrentCharacter();
            return nextToken();
        }

        if(is('/')){
            updateLexeme();
            updateCurrentCharacter();
            lexeme = "";
            return initial();
        }

        updateLexeme();
        updateCurrentCharacter();
        return multiLineCommentInitial();
    }

    private Token openingParenthesis(){
        return new Token("openingParenthesis" , lexeme, getLineNumber());
    }
    private Token closingParenthesis(){
        return new Token("closingParenthesis" , lexeme, getLineNumber());
    }


    private Token openingBrace(){
        return new Token("openingBrace" , lexeme, getLineNumber());
    }
    private Token closingBrace(){
        return new Token("closingBrace" , lexeme, getLineNumber());
    }


    private Token semicolon(){
        return new Token("semicolon" , lexeme, getLineNumber());
    }


    private Token comma(){
        return new Token("comma" , lexeme, getLineNumber());
    }


    private Token dot(){
        return new Token("dot" , lexeme, getLineNumber());
    }


    private Token colon(){
        return new Token("colon" , lexeme, getLineNumber());
    }


    private Token EOF(){
        return new Token("EOF", lexeme, getLineNumber());
    }
}
