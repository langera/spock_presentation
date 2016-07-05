import org.langera.spockextensions.alert.Alert
import spock.lang.Specification


class AlertSpec extends Specification {

    @Alert
    def 'my hovercraft is full of eels'() {
        expect:
            1 + 2 == 4
    }
}