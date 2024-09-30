package org.jitpack.practice;

public class Lexer {

    private final String text;
    private int position;

    public Lexer(String text) {
        this.text = text;
        this.position = 0;
    }

    public Token nextToken() throws UnexpectedTokenException{
        while (position < text.length() && Character.isSpaceChar(text.charAt(position))) {
            position++;
        }

        if (position >= text.length()) {
            return new Token(TokenType.EOF, null);
        }

        var current = text.charAt(position);
        position++;
        return switch (current) {
            case '{' -> new Token(TokenType.LEFT_BRACE, "{");
            case '}' -> new Token(TokenType.RIGHT_BRACE, "}");
            default -> throw new UnexpectedTokenException("Unexpected token: " + current);
        };
    }
}
