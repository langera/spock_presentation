import org.langera.spock.Publisher
import org.langera.spock.Subscriber
import spock.lang.Specification
import spock.lang.Subject

class PublisherSpec extends Specification {

    @Subject
    private Publisher publisher = new Publisher()

    def 'events are published to all subscribers'() {
        given:
            Subscriber subscriber1 = Mock(Subscriber)
            Subscriber subscriber2 = Mock(Subscriber)
            Subscriber subscriber3 = Mock(Subscriber)
            publisher.add(subscriber1)
            publisher.add(subscriber2)
        when:
            publisher.publish('hello')
        then:
            1 * subscriber1.receive('hello')
            1 * subscriber2.receive('hello')
            0 * subscriber3.receive(_)
    }

    def 'events are published to all subscribers in order'() {
        given:
            Subscriber subscriber1 = Mock(Subscriber)
            Subscriber subscriber2 = Mock(Subscriber)
            publisher.add(subscriber1)
            publisher.add(subscriber2)
        when:
            publisher.publish('hello')
        then:
            1 * subscriber1.receive('hello')
        then:
            1 * subscriber2.receive('hello')
    }

    def 'events are published - scope of mock assertion'() {
        given:
            Subscriber subscriber1 = Mock(Subscriber)
            publisher.add(subscriber1)
        when:
            publisher.publish('hello')
        then:
            1 * subscriber1.receive('hello')
        when:
            publisher.publish('hola')
        then:
            1 * subscriber1.receive('hola')
    }

    def 'publisher recovers subscriber exception'() {
        given:
            Subscriber subscriber1 = Mock(Subscriber)
            Subscriber subscriber2 = Stub(Subscriber)
            publisher.add(subscriber1)
            publisher.add(subscriber2)
            subscriber1.receive('hello') >> { throw new RuntimeException() }
        when:
            publisher.publish('hello')
        then:
            (1.._) * subscriber2.receive('hello')
    }

    @SuppressWarnings("GroovyAssignabilityCheck")


    def 'publisher recovers subscriber exception mock and stub'() {
        given:
            Subscriber subscriber1 = Mock(Subscriber)
            Subscriber subscriber2 = Mock(Subscriber)
            publisher.add(subscriber1)
            publisher.add(subscriber2)
        when:
            publisher.publish('hello')
        then:
            1 * subscriber1.receive('hello') >> { throw new RuntimeException()}
            1 * subscriber2.receive('hello')
    }

    def 'events are published to all subscribers with regex mocking'() {
        def subscriber1 = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        def publisher = new Publisher()
        publisher.subscribers << subscriber1 << subscriber2
        when:
            publisher.publish('hello')
        then:
            1 * subscriber1./rec.*/(!null)
            1 * subscriber2.receive(_ as String)
    }

    def 'events are published to all subscribers with abbr mocking'() {
        def subscriber1 = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        def publisher = new Publisher()
        publisher.subscribers << subscriber1 << subscriber2
        when:
            publisher.publish('hello')
        then:
            2 * _.receive('hello')
    }

    @SuppressWarnings("GroovyVariableNotAssigned")
    def "events are published a subscriber throws an exception"() {
        def subscriber = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)

        def publisher = new Publisher()
        publisher.subscribers << subscriber << subscriber2

                                                        //        def process = Mock(ProcessBuilder)

        when:
            publisher.publish('hello')
        then:
            1 * subscriber.receive('hello') >> { throw new Exception() }
        then:
            0 * subscriber2.receive('hello')
        then:
            thrown Exception

//
//        1 * subscriber.receive('hello')                                                                                                // an argument that is equal to the String 'hello'
//        1 * subscriber.receive(!'hello')                                                                                                  // an argument that is unequal to the String 'hello'
//        1 * subscriber.receive(_)                                                                                                          // any single argument (including null)
//        1 * subscriber.receive(*_)                                                                                                      // any argument list (including the empty argument list)
//        1 * subscriber.receive(!null)                                                                                                    // any non-null argument
//        1 * subscriber.receive(_ as String)                                                                                             // any non-null argument that is-a String
//        1 * subscriber.receive({ it.length > 3 })                                                                                        // an argument that satisfies the given predicate
//        1 * process.command('ls', '-a', _, !null, { ['abc'].contains(it) })
//
//
//
//
//
//
//
//
//        1 * subscriber.receive('hello')                                                                                                  // exactly one call
//        0 * subscriber.receive('hello')                                                              // zero calls
//        (1..3) * subscriber.receive('hello')                                                         // between one and three calls (inclusive)
//        (1.._) * subscriber.receive('hello')                                                         // at least one call
//        (_..3) * subscriber.receive('hello')                                                         // at most three calls
//        _ * subscriber.receive('hello')                                                              // any number of calls, including zero
//        3 * subscriber.receive('hello') >>> [true, false, { throw new Exception()}]
//
    }

    def 'spy example'() {
        given:
            def subscriber = Spy(Subscriber, constructorArgs: ["Fred"])
            publisher.add(subscriber)
        when:
            publisher.publish('hello')
        then:
            subscriber.receive(_) >>
                    { String message -> callRealMethod(); message.size() > 3 ? 'ok' : 'fail' }
    }
}