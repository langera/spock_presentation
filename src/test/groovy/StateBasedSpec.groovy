import org.langera.spock.Account
import spock.lang.Specification

class StateBasedSpec extends Specification {

    def 'old balance of 1 after deposit of 5 is 6'() {
        given:
            Account account = new Account(1)
        when:
            account.deposit(5)
        then:
            account.balance == 6
            old(account.balance) == 1
    }



    def 'deposit should increment balance'() {
        given:
            Account account = new Account(1)
        when:
            account.deposit(5)
        then:
            account.balance == 6
    }

    def 'negative amount is illegal'() {
        given:
            Account account = new Account(1)
        when:
            account.withdraw(-5)
        then:
            thrown IllegalArgumentException
    }

    def 'negative amount is illegal with specific message'() {
        given:
            Account account = new Account(1)
        when:
            account.withdraw(-5)
        then:
            Exception e = thrown IllegalArgumentException
            e.message == 'amount: -5'
    }

    def 'balance reflects all deposits and withdraws'() {
        given:
            Account account = new Account(0)
        when:
            account.deposit(5)
        then:
            account.balance == 5
        and:
            account.withdraw(2)
        then:
            account.balance == 3
        and:
            account.withdraw(2)
        then:
            account.balance == 1
    }

}

