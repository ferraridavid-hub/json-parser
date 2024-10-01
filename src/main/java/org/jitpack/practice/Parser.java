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

    private void parseValue() throws UnexpectedTokenException {
        if (currentToken.getType().equals(TokenType.STRING))
            eat(TokenType.STRING);
        else if (currentToken.getType().equals(TokenType.BOOLEAN))
            eat(TokenType.BOOLEAN);
        else if (currentToken.getType().equals(TokenType.NULL))
            eat(TokenType.NULL);
        else if (currentToken.getType().equals(TokenType.LEFT_SQUARE_BRACE)) {
            eat(TokenType.LEFT_SQUARE_BRACE);
            if (!currentToken.getType().equals(TokenType.RIGHT_SQUARE_BRACE)) {
                parseValues();
            }
            eat(TokenType.RIGHT_SQUARE_BRACE);
        }
    }

    private void parseValues() throws UnexpectedTokenException {
        parseValue();
        while(currentToken.getType().equals(TokenType.COMMA)) {
            eat(TokenType.COMMA);
            parseValue();
        }
    }

    private void parseKeyValuePair() throws UnexpectedTokenException {
        eat(TokenType.STRING);
        eat(TokenType.COLON);
        parseValue();
    }

    private void parseKeyValuePairs() throws UnexpectedTokenException {
        parseKeyValuePair();
        while (currentToken.getType().equals(TokenType.COMMA)) {
            eat(TokenType.COMMA);
            parseKeyValuePair();
        }
    }

    public void parse() throws UnexpectedTokenException {
        eat(TokenType.LEFT_BRACE);
        if (!currentToken.getType().equals(TokenType.RIGHT_BRACE)) {
            parseKeyValuePairs();
        }
        eat(TokenType.RIGHT_BRACE);
        eat(TokenType.EOF);
    }
}
