import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();


    @Test
    void shouldEvaluateSimpleAddition() {
        double result = evaluator.evaluate("2 + 3", Map.of());
        assertEquals(5.0, result);
    }

    @Test
    void shouldRespectOperatorPrecedence() {
        double result = evaluator.evaluate("2 + 3 * 4", Map.of());
        assertEquals(14.0, result);
    }

    @Test
    void shouldHandleParentheses() {
        double result = evaluator.evaluate("(2 + 3) * 4", Map.of());
        assertEquals(20.0, result);
    }

    @Test
    void shouldHandleDivision() {
        double result = evaluator.evaluate("10 / 2", Map.of());
        assertEquals(5.0, result);
    }


    @Test
    void shouldHandleDecimals() {
        double result = evaluator.evaluate("2.5 + 1.5", Map.of());
        assertEquals(4.0, result);
    }


    @Test
    void shouldEvaluateWithVariables() {
        double result = evaluator.evaluate("x + 3", Map.of("x", 5.0));
        assertEquals(8.0, result);
    }

    @Test
    void shouldEvaluateMultipleVariables() {
        double result = evaluator.evaluate("a * b + c",
                Map.of("a", 2.0, "b", 3.0, "c", 4.0));
        assertEquals(10.0, result);
    }

    @Test
    void shouldExtractVariables() {
        Set<String> vars = evaluator.extractVariables("a + b * c");
        assertEquals(Set.of("a", "b", "c"), vars);
    }


    @Test
    void shouldThrowOnEmptyExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("", Map.of()));
    }

    @Test
    void shouldThrowOnNullExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate(null, Map.of()));
    }

    @Test
    void shouldThrowOnUnknownVariable() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("x + 2", Map.of()));
    }

    @Test
    void shouldThrowOnDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> evaluator.evaluate("10 / 0", Map.of()));
    }

    @Test
    void shouldThrowOnInvalidCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("2 + $", Map.of()));
    }

    @Test
    void shouldThrowOnInvalidNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("1..2 + 3", Map.of()));
    }

    @Test
    void shouldIgnoreSpaces() {
        double result = evaluator.evaluate("  2   +   3  ", Map.of());
        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowOnInvalidOperatorPlacement() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("+ 2 3", Map.of()));
    }

    @Test
    void shouldThrowOnInvalidExpressionStructure() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("2 2 + 3", Map.of()));
    }

    @Test
    void shouldThrowOnEndingWithOperator() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("2 + 3 +", Map.of()));
    }

    @Test
    void shouldThrowOnUnmatchedParentheses() {
        assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluate("(2 + 3", Map.of()));
    }


    @Test
    void shouldEvaluateComplexExpression() {
        double result = evaluator.evaluate("2 + 3 * (4 + 1)", Map.of());
        assertEquals(17.0, result);
    }

    @Test
    void shouldEvaluateNestedParentheses() {
        double result = evaluator.evaluate("(2 + (3 * 2)) * 2", Map.of());
        assertEquals(16.0, result);
    }

}