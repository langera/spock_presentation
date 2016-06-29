import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AssertTest {

    @Test
    public void assertCalculation() {
        assertEquals(16, (2 + 2) * 3 + 5);
    }
}
