package org.jitpack.practice;

public class Parser {

    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) throws UnexpectedTokenException {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    private void eat(TokenType type) throws UnexpectedTokenException, InvalidSyntaxException {
        if (currentToken.getType().equals(type)) {
            currentToken = lexer.nextToken();
        } else {
            throw new InvalidSyntaxException("Invalid syntax: " + currentToken.getValue());
        }
    }

    public void parse() throws UnexpectedTokenException, InvalidSyntaxException {
        eat(TokenType.LEFT_BRACE);
        eat(TokenType.RIGHT_BRACE);
        eat(TokenType.EOF);
    }
}
