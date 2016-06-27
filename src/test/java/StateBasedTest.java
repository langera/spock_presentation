import org.assertj.core.api.Assertions;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.langera.spock.Account;

import static org.junit.Assert.assertEquals;

public class StateBasedTest {

    @Test
    public void oldBalanceOf1AfterDepositOf5Is6() {
        // given
        final Account account = new Account(1);
        final int oldBalance = account.getBalance();
        // when
        account.deposit(5);
        // then
        assertEquals(6, account.getBalance());
        assertEquals(1, oldBalance);
    }

    @Test
    public void depositShouldIncrementBalance() throws Exception {
        // given
        final Account account = new Account(1);
        // when
        account.deposit(5);
        // then
        assertEquals(6, account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeAmountIsIllegal() throws Exception {
        final Account account = new Account(1);
        account.withdraw(-5);
    }

    @Test
    public void negativeAmountIsIllegalWithSpecificMessage() throws Exception {
        final Throwable thrown =
            Assertions.catchThrowable(() -> {
                final Account account = new Account(1);
                account.withdraw(-5);
            });
        Assert.assertThat(thrown, IsInstanceOf.instanceOf(IllegalArgumentException.class));
        Assertions.assertThat(thrown).hasMessageContaining("amount: -5");
    }

    @Test
    public void balanceReflectsAllDepositsAndWithdraws() throws Exception {
        final Account account = new Account(0);
        account.deposit(5);
        assertEquals(5, account.getBalance());
        account.withdraw(2);
        assertEquals(3, account.getBalance());
        account.withdraw(2);
        assertEquals(1, account.getBalance());
    }
}
