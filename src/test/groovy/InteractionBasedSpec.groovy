import org.langera.spock.Account
import org.langera.spock.AccountRepository
import org.langera.spock.Bank
import spock.lang.Specification


class InteractionBasedSpec extends Specification {

    private AccountRepository accountRepository
    private Bank bank

    def setup() {
        accountRepository = Mock(AccountRepository)
        bank  = new Bank(accountRepository)
    }

    def 'money transfer withdraws from account and deposit to another'() {
        given:
            Account myAccount = new Account(17)
            Account someoneAccount = new Account(0)
            accountRepository.getAccount('me') >> myAccount
            accountRepository.getAccount('someone') >> someoneAccount
        when:
            bank.transferMoney('me', 'someone', 17)
        then:
            myAccount.balance == 0
            someoneAccount.balance == 17
    }

    def 'money transfer only calls once'() {
        given:
            Account myAccount = new Account(17)
            Account someoneAccount = new Account(0)
        when:
            bank.transferMoney('me', 'someone', 17)
        then:
            1 * accountRepository.getAccount('me') >> myAccount
            1 * accountRepository.getAccount('someone') >> someoneAccount
            myAccount.balance == 0
            someoneAccount.balance == 17
    }

}