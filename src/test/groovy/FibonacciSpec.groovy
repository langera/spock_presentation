import spock.lang.Specification
import spock.lang.Unroll

import static org.langera.spock.Fibonacci.fib



class FibonacciSpec extends Specification {

    def 'fib of negative number is illegal'() {
        when:
            fib(-1)
        then:
            thrown IllegalArgumentException
    }

    @Unroll
    def '#iterationCount: fib(#n) = #expected'() {
        expect:
            fib(n) == expected
        where:
            n | expected
            0 | 0
            1 | 1
            2 | 1
            3 | 2
            4 | 3
            5 | 5
            6 | 8
            7 | 13
    }
}