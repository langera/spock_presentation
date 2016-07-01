import org.langera.spock.Publisher
import org.langera.spock.Subscriber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.annotation.Resource

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
class SpringExtensionSpec extends Specification {

    @Resource // injected by name
    def myPublisher;

    @Autowired // injected by type
    Subscriber subscriber;

    @Autowired
    ApplicationContext applicationContext;

    def 'test my spring config'() {
        expect:
            myPublisher == applicationContext.getBean(Publisher)
            subscriber instanceof Subscriber
    }
}