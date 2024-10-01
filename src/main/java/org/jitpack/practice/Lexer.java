package org.jitpack.practice;

public class Lexer {

    private final String text;
    private int position;

    public Lexer(String text) {
        this.text = text;
        this.position = 0;
    }

    private String tokenizeString() throws UnexpectedTokenException {
        int j = position + 1;
        while (j < text.length() && text.charAt(j) != '"') {
            j++;
        }
        if (j >= text.length()) {
            throw new UnexpectedTokenException("Matching \" not found.");
        }
        return text.substring(position, j + 1);
    }

    private String tokenizeBoolean(boolean b) throws UnexpectedTokenException {
        String boolString = Boolean.toString(b);
        String sub = text.substring(position, position + boolString.length());
        if (sub.equals(boolString)) {
            return sub;
        }
        throw new UnexpectedTokenException("" + text.charAt(position));
    }

    private String tokenizeNull() throws UnexpectedTokenException {
        var nullString = "null";
        String sub = text.substring(position, position + nullString.length());
        if (sub.equals(nullString)) {
            return sub;
        }
        throw new UnexpectedTokenException("" + text.charAt(position));
    }

    private String tokenizeNumber() throws UnexpectedTokenException {
        int j = position;
        if (text.charAt(j) == '0' && text.charAt(j + 1) != '.') {
            throw new UnexpectedTokenException("0");
        }
        while (j < text.length()
                && (Character.isDigit(text.charAt(j)) || (text.charAt(j) == '.' && Character.isDigit(text.charAt(j + 1))))) {
            j++;
        }
        return text.substring(position, j);
    }

    public Token nextToken() throws UnexpectedTokenException {
        while (position < text.length() && Character.isWhitespace(text.charAt(position))) {
            position++;
        }

        if (position >= text.length()) {
            return new Token(TokenType.EOF, null);
        }

        var current = text.charAt(position);
        switch (current) {
            case '{':
                position++;
                return new Token(TokenType.LEFT_BRACE, "{");
            case '}':
                position++;
                return new Token(TokenType.RIGHT_BRACE, "}");
            case '[':
                position++;
                return new Token(TokenType.LEFT_SQUARE_BRACE, "[");
            case ']':
                position++;
                return new Token(TokenType.RIGHT_SQUARE_BRACE, "]");
            case ':':
                position++;
                return new Token(TokenType.COLON, ":");
            case ',':
                position++;
                return new Token(TokenType.COMMA, ",");
            case '"':
                var message = tokenizeString();
                position += message.length();
                return new Token(TokenType.STRING, message);
            case 'f':
                var boolFalse = tokenizeBoolean(false);
                position += boolFalse.length();
                return new Token(TokenType.BOOLEAN, boolFalse);
            case 't':
                var boolTrue = tokenizeBoolean(true);
                position += boolTrue.length();
                return new Token(TokenType.BOOLEAN, boolTrue);
            case 'n':
                var nullString = tokenizeNull();
                position += nullString.length();
                return new Token(TokenType.NULL, nullString);
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                var numberString = tokenizeNumber();
                position += numberString.length();
                return new Token(TokenType.NUMBER, numberString);
            default:
                throw new UnexpectedTokenException("" + current);
        }
    }
}
