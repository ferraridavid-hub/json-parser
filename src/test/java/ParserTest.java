import org.jitpack.practice.Lexer;
import org.jitpack.practice.Parser;
import org.jitpack.practice.UnexpectedTokenException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testEmptyJsonObject() {
        checkValidJson("{}");
    }

    @Test
    public void testJsonWithWithspaces() {
        checkValidJson("  { \n }  \t");
    }

    @Test
    public void testInvalidTokenJson() {
        checkInvalidJson("{alfa}");
    }

    @Test
    public void testInvalidSyntaxJson() {
        checkInvalidJson("{}{");
    }

    @Test
    public void testStringToStringJson() {
        checkValidJson("{\"name\":\"david\"}");
    }

    @Test
    public void testMultpleStringToStringJson() {
        checkValidJson("{\"name\": \"david\", \"lastName\":\"ferrari\"}");
    }

    @Test
    public void testInvalidMultipleStringToStringJson() {
        checkInvalidJson("{\"name\": \"david\", \"lastName\":\"ferrari\", \"job\":\"Software Engineer\",}");
    }

    @Test
    public void testInvalidMultipleCommas() {
        checkInvalidJson("{\"name\": \"david\",, \"lastName\":\"ferrari\"}");
    }

    @Test
    public void testJsonWithBoolean() {
        var json = """
                {
                    "name": "david",
                    "lastName": "ferrari",
                    "isAdmin": false,
                    "isPremium": true
                }
                """;
        checkValidJson(json);
    }

    @Test
    public void testJsonWithNull() {
        checkValidJson("{\"name\":null}");
    }

    @Test
    public void testInvalidJsonWithNullKey(){
        checkInvalidJson("{null: true}");
    }

    @Test
    public void testInvalidJsonWithBooleanKey() {
        var json = """
                {
                    "name": "david",
                    "lastName": "ferrari",
                    "isAdmin": false,
                    true: "isPremium"
                }
                """;
        checkInvalidJson(json);
    }

    @Test
    public void testJsonWithList() {
        checkValidJson("{\"names\" : [true, false, \"name\", [[null, true]]]}");
    }

    @Test
    public void testInvalidJsonWithList() {
        checkInvalidJson("{[]:true}");
    }

    @Test
    public void testComplexJsonWithNestedStructures() {
        var json = """
                {
                    "name": "david",
                    "lastName": "ferrari",
                    "isAdmin": false,
                    "partnership": null,
                    "courses": ["ml crash course", "ethical hacking", "dsa in go"],
                    "experience": {
                                        "java": "2 years",
                                        "docker": "1 year",
                                        "burpsuite": "1 year"
                                    },
                    "partnership": 2,
                    "numbers": [1, 2, 3, 45, 6, 3.2, 1.2],
                    "probability": 0.43
                }
                """;
        checkValidJson(json);
    }

    private static void checkValidJson(String json) {
        var parser = assertDoesNotThrow(() -> new Parser(new Lexer(json)));
        assertDoesNotThrow(parser::parse);
    }

    private static void checkInvalidJson(String json) {
        var parser = assertDoesNotThrow(() -> new Parser(new Lexer(json)));
        assertThrows(UnexpectedTokenException.class, parser::parse);
    }

}
