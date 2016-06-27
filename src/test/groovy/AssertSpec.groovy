import spock.lang.Specification

class AssertSpec extends Specification {

    def 'assert calculation'() {
        expect:
            (2 + 2) * 3 + 5 == 16
    }
}


