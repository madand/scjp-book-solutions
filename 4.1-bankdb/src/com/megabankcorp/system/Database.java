package com.megabankcorp.system;

import com.megabankcorp.records.Account;

/**
 * Base class for classes that implement a sample banking system.
 */
abstract public class Database {
    /**
     * Deposit the given amount into the given account.
     *
     * @param amount the amount to deposit.
     * @param account the account to deposit into.
     */
    abstract public void deposit(double amount, Account account);

    /**
     * Withdraw the given amount from the given account.
     *
     * @param amount the amount to withdraw.
     * @param account the account to withdraw from.
     */
    abstract public void withdraw(double amount, Account account);

    /**
     * Return the current balance of the given account.
     *
     * @param account the account to get the balance of.
     * @return the current balance of the account.
     */
    abstract protected double balance(Account account);

    /**
     * Transfer the given amount from one account to another.
     *
     * @param amount the amount to transfer.
     * @param src the account to transfer from (the source).
     * @param dst the account to transfer to (the destination).
     */
    synchronized final void transfer(double amount, Account src, Account dst) throws InsufficientFundsException {
        if (balance(src) < amount) {
            throw new InsufficientFundsException(src, amount);
        }

        withdraw(amount, src);
        deposit(amount, dst);
    }
}

/**
 * Exception thrown when trying to transfer funds from an account with insufficient balance.
 */
class InsufficientFundsException extends Exception {
    private final Account account;
    private final double requestedAmount;

    InsufficientFundsException(Account account, double requestedAmount) {
        super(String.format("Current balance of the %s account is lower than %.2f.", account, requestedAmount));
        this.account = account;
        this.requestedAmount = requestedAmount;
    }

    public Account getAccount() {
        return account;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
