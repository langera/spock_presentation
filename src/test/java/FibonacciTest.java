import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.langera.spock.Fibonacci.fib;



@RunWith(Parameterized.class)
public class FibonacciTest {

    @Parameterized.Parameters( name = "{index}: fib({0})={1}" )
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]
            { { 0, 0 }, { 1, 1 }, { 2, 1 },
            { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
    }

    private final int input;
    private final int expected;

    public FibonacciTest(final int input, final int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testFib() {
        assertEquals(expected, fib(input));
    }
}