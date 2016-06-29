package org.langera.spock;

public class Account {

    private int balance;

    public Account(final int balance) {
        deposit(balance);
    }

    public void deposit(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount: " + amount);
        }
        balance += amount;
    }

    public void withdraw(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount: " + amount);
        }
        balance -= amount;
    }

    public boolean canWithdraw(final int amount) {
        return balance >= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void clean() {
        balance = 0;
    }
}
