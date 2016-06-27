import spock.lang.IgnoreIf
import spock.lang.Requires
import spock.lang.Specification

class RequiresSpec  extends Specification {

    @IgnoreIf({ !jvm.java8 })
    def 'should check list stream size'() {
        given:
            List items = [ 1, 2, 3, 4]
        expect:
            items.stream().count() == 4
    }

    @Requires({ os.linux || os.macOs })
    def 'should check my log dir linux style'() {
        expect:
            new File('/var/log/myProgram').exists()
    }

    @Requires({ os.windows })
    def 'should check my log dir windows style'() {
        expect:
            new File('c:\\myProgram\\logs').exists()
    }

}


