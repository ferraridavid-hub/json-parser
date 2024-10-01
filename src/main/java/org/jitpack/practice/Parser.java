package org.jitpack.practice;

public class Parser {

    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) throws UnexpectedTokenException {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    private void eat(TokenType type) throws UnexpectedTokenException {
        if (currentToken.getType().equals(type)) {
            currentToken = lexer.nextToken();
        } else {
            throw new UnexpectedTokenException(currentToken.getValue());
        }
    }

    private void parseStringToString() throws UnexpectedTokenException {
        eat(TokenType.STRING);
        eat(TokenType.COLON);
        eat(TokenType.STRING);
    }

    public void parse() throws UnexpectedTokenException {
        eat(TokenType.LEFT_BRACE);
        if (currentToken.getType().equals(TokenType.RIGHT_BRACE)) {
            eat(TokenType.RIGHT_BRACE);
        } else {
            parseStringToString();
            while (currentToken.getType().equals(TokenType.COMMA)) {
                eat(TokenType.COMMA);
                parseStringToString();
            }
            eat(TokenType.RIGHT_BRACE);
        }
        eat(TokenType.EOF);
    }
}
