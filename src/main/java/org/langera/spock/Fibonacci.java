package org.langera.spock;

public class Fibonacci {

    public static int fib(final int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
