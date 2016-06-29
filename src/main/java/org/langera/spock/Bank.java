package org.langera.spock;

public class Bank {

    private final AccountRepository accountRepository;

    public Bank(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(final String from, final String to, final int amount) {
        final Account fromAccount = accountRepository.getAccount(from);
        final Account toAccount = accountRepository.getAccount(to);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
