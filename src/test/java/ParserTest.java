import com.sun.jdi.InvalidLineNumberException;
import org.jitpack.practice.InvalidSyntaxException;
import org.jitpack.practice.Lexer;
import org.jitpack.practice.Parser;
import org.jitpack.practice.UnexpectedTokenException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testEmptyJsonObject() throws UnexpectedTokenException, InvalidSyntaxException {
        var parser = new Parser(new Lexer("{}"));
        assertDoesNotThrow(parser::parse);
    }

    @Test
    public void testJsonWithWithspaces() throws UnexpectedTokenException, InvalidSyntaxException {
        var parser = new Parser(new Lexer("  { \n }  \t"));
        assertDoesNotThrow(parser::parse);
    }

    @Test
    public void testInvalidTokenJson() throws UnexpectedTokenException, InvalidSyntaxException {
        var parser = new Parser(new Lexer("{alfa}"));
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void testInvalidSyntaxJson() throws UnexpectedTokenException, InvalidSyntaxException {
        var parser = new Parser(new Lexer("{}{"));
        assertThrows(InvalidSyntaxException.class, parser::parse);
    }

}
