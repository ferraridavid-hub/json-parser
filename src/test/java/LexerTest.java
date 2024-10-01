import org.jitpack.practice.Lexer;
import org.jitpack.practice.Token;
import org.jitpack.practice.TokenType;
import org.jitpack.practice.UnexpectedTokenException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    public void testEmptyJsonObject() {
        var lexer = new Lexer("{}");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.RIGHT_BRACE, "}");
        var expectedToken3 = new Token(TokenType.EOF, null);

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test
    public void testJsonWithSpaces(){
        var lexer = new Lexer("     {   }   ");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);


        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.RIGHT_BRACE, "}");
        var expectedToken3 = new Token(TokenType.EOF, null);

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test
    public void testInvalidJson() {
        var lexer = new Lexer(" {alfa}");
        assertDoesNotThrow(lexer::nextToken);
        assertThrows(UnexpectedTokenException.class, lexer::nextToken);
    }

    @Test
    public void testJsonWithColonAndWhiteSpaces() {
        var lexer = new Lexer("\t{\"name\": \"david\"\n}");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);
        var token4 = assertDoesNotThrow(lexer::nextToken);
        var token5 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.STRING, "\"name\"");
        var expectedToken3 = new Token(TokenType.COLON, ":");
        var expectedToken4 = new Token(TokenType.STRING, "\"david\"");
        var expectedToken5 = new Token(TokenType.RIGHT_BRACE, "}");

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
        assertEquals(expectedToken4, token4);
        assertEquals(expectedToken5, token5);
    }

    @Test
    public void testString() {
        var lexer = new Lexer("\"alfa\"");
        var token = assertDoesNotThrow(lexer::nextToken);
        var expectedToken = new Token(TokenType.STRING, "\"alfa\"");
        assertEquals(expectedToken, token);
    }

    @Test
    public void testComma() {
        var lexer = new Lexer(",,,");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.COMMA, ",");
        var expectedToken2 = new Token(TokenType.COMMA, ",");
        var expectedToken3 = new Token(TokenType.COMMA, ",");

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test
    public void testInvalidString() {
        var lexer = new Lexer("\"beta");
        assertThrows(UnexpectedTokenException.class, lexer::nextToken);
    }

    @Test
    public void testInvalidToken() {
        var lexer = new Lexer("gamm\"");
        assertThrows(UnexpectedTokenException.class, lexer::nextToken);
    }

    @Test
    public void testFalseBooleanToken() {
        var lexer = new Lexer("false");
        var token = assertDoesNotThrow(lexer::nextToken);
        var expectedToken = new Token(TokenType.BOOLEAN, "false");
        assertEquals(expectedToken, token);
    }

    @Test
    public void testTrueBooleanToken() {
        var lexer = new Lexer("{true}");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.BOOLEAN, "true");
        var expectedToken3 = new Token(TokenType.RIGHT_BRACE, "}");

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test
    public void testNullToken() {
        var lexer = new Lexer(",null,null");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);
        var token4 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.COMMA, ",");
        var expectedToken2 = new Token(TokenType.NULL, "null");
        var expectedToken3 = new Token(TokenType.COMMA, ",");
        var expectedToken4 = new Token(TokenType.NULL, "null");

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
        assertEquals(expectedToken4, token4);
    }

    @Test
    public void testListToken() {
        var lexer = new Lexer("{[]}");
        var token1 = assertDoesNotThrow(lexer::nextToken);
        var token2 = assertDoesNotThrow(lexer::nextToken);
        var token3 = assertDoesNotThrow(lexer::nextToken);
        var token4 = assertDoesNotThrow(lexer::nextToken);

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.LEFT_SQUARE_BRACE, "[");
        var expectedToken3 = new Token(TokenType.RIGHT_SQUARE_BRACE, "]");
        var expectedToken4 = new Token(TokenType.RIGHT_BRACE, "}");

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
        assertEquals(expectedToken4, token4);
    }
}
