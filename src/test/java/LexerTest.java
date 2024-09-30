import org.jitpack.practice.Lexer;
import org.jitpack.practice.Token;
import org.jitpack.practice.TokenType;
import org.jitpack.practice.UnexpectedTokenException;
import org.junit.Test;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testEmptyJsonObject() throws UnexpectedTokenException {
        var lexer = new Lexer("{}");
        var token1 = lexer.nextToken();
        var token2 = lexer.nextToken();
        var token3 = lexer.nextToken();

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.RIGHT_BRACE, "}");
        var expectedToken3 = new Token(TokenType.EOF, null);

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test
    public void testJsonWithSpaces() throws Exception {
        var lexer = new Lexer("     {   }   ");
        var token1 = lexer.nextToken();
        var token2 = lexer.nextToken();
        var token3 = lexer.nextToken();

        var expectedToken1 = new Token(TokenType.LEFT_BRACE, "{");
        var expectedToken2 = new Token(TokenType.RIGHT_BRACE, "}");
        var expectedToken3 = new Token(TokenType.EOF, null);

        assertEquals(expectedToken1, token1);
        assertEquals(expectedToken2, token2);
        assertEquals(expectedToken3, token3);
    }

    @Test(expected = UnexpectedTokenException.class)
    public void testInvalidToken() throws UnexpectedTokenException{
        var lexer = new Lexer(" {alfa}");
        lexer.nextToken();
        lexer.nextToken();
    }
}
