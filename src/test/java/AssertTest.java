import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AssertTest {

    @Test
    public void assertCalculation() throws Exception {
        assertEquals(16, (2 + 2) * 3 + 5);
    }
}
