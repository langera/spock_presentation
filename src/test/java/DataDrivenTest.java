import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.langera.spock.Account;


@RunWith(Parameterized.class)
public class DataDrivenTest {

    private int initial;
    private int amount;
    private int expected;

    public DataDrivenTest(final int initial, final int amount, final int expected) {
        this.initial = initial;
        this.amount = amount;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
            new Object[]{ 0, 0, 0 },
            new Object[]{ 0, 1, 1 },
            new Object[]{ 0, 2, 2 },
            new Object[]{ 1, 1, 2 },
            new Object[]{ 2, 1, 666 }
        };
    }

    @Test
    public void depositToAccount() throws Exception {
      final Account account = new Account(initial);
      account.deposit(amount);
      Assert.assertEquals(expected, account.getBalance());
    }
}
