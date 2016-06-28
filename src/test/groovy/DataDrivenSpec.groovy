import org.langera.spock.Account
import spock.lang.Specification
import spock.lang.Unroll

class DataDrivenSpec extends Specification {

    @Unroll
    def 'deposit #amount to #initial amount = #expected'() {
        given:
            Account account = new Account(initial)
        when:
            account.deposit(amount)
        then:
            account.balance == expected
        where:
            initial << [0, 1, 2, 3]
            amount << [1, 2, 3, 4]
            expected << [1, 3, 5, 7]
    }

    @Unroll
    def 'depositing #amount to #initial amount = #expected'() {
        given:
            Account account = new Account(initial)
        when:
            account.deposit(amount)
        then:
            account.balance == expected
        where:
            initial| amount || expected
              0    | 0      || 0
              0    | 1      || 1
              0    | 2      || 2
              1    | 2      || 3
              2    | 1      || 3
    }

    @Unroll
    def '#value is illegal'() {
        given:
            Account account = new Account(0)
        when:
            account.deposit(value)
        then:
            thrown IllegalArgumentException
        where:
            value << generateIllegalValues()
    }

    private static generateIllegalValues() {
        return [-1, -2]
    }
}