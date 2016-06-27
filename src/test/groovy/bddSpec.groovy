import spock.lang.Specification


class bddSpec extends Specification {

    def 'should verify account balance is calculated correctly'() {
        given: 'account with initial balance 0'
        when: 'we deposit 5'
        then: 'balance is 5'
        and: 'deposit of 2 more'
        then: 'balance is 7'
        and: 'withdraw of 8'
        then: 'not enough money. failure'
    }

}