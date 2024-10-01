import org.jitpack.practice.Lexer;
import org.jitpack.practice.Parser;
import org.jitpack.practice.UnexpectedTokenException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testEmptyJsonObject() {
        var parser = assertDoesNotThrow(() -> new Parser(new Lexer("{}")));
        assertDoesNotThrow(parser::parse);
    }

    @Test
    public void testJsonWithWithspaces() {
        var parser = assertDoesNotThrow(() -> new Parser(new Lexer("  { \n }  \t")));
        assertDoesNotThrow(parser::parse);
    }

    @Test
    public void testInvalidTokenJson() {
        var parser = assertDoesNotThrow(() ->new Parser(new Lexer("{alfa}")));
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void testInvalidSyntaxJson() {
        var parser = assertDoesNotThrow( () -> new Parser(new Lexer("{}{")));
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

    @Test
    public void testStringToStringJson() {
        var parser = assertDoesNotThrow(() -> new Parser(new Lexer("{\"name\":\"david\"}")));
        assertDoesNotThrow(parser::parse);
    }

}
