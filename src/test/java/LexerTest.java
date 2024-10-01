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
    public void testString() {
        var lexer = new Lexer("\"alfa\"");
        var token = assertDoesNotThrow(lexer::nextToken);
        var expectedToken = new Token(TokenType.STRING, "\"alfa\"");
        assertEquals(expectedToken, token);
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
}
