package org.jitpack.practice;

public class Lexer {

    private final String text;
    private int position;

    public Lexer(String text) {
        this.text = text;
        this.position = 0;
    }

    private String message() throws UnexpectedTokenException {
        int j = position + 1;
        while (j < text.length() && text.charAt(j) != '"') {
            j++;
        }
        if (j >= text.length()) {
            throw new UnexpectedTokenException("Matching \" not found.");
        }
        return text.substring(position, j + 1);
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
            case ':':
                position++;
                return new Token(TokenType.COLON, ":");
            case '"':
                var message = message();
                position += message().length();
                return new Token(TokenType.STRING, message);
            default:
                throw new UnexpectedTokenException("" + current);
        }
    }
}
