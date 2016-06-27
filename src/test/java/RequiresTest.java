import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RequiresTest {


    @Test
    //@Ignore
    public void shouldCheckListStreamSize() throws Exception {
        if (System.getProperty("java.version").startsWith("1.8")) {
            final List<Integer> items = Arrays.asList(1, 2, 3, 4);

            Assert.assertEquals(4, items.stream().count());
        }
    }
}
