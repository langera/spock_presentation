import spock.lang.Specification
import spock.lang.Stepwise



@Stepwise
class StepwiseSpec extends Specification {

    def "step 1"() {
        expect: true
    }

    def "step 2"() {
        expect: false
    }

    def "step 3"() {
        expect: 'we will not reach this step'
            true
    }
}