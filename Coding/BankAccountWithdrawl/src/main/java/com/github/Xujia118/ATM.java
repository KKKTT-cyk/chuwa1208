package com.github.Xujia118;

class ATM {
    public void processWithdrawal(BankAccount account, double amount) throws InsufficientBalanceException {
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            // Local logging/handling
            System.err.println("[LOG] ATM Alert: Insufficient funds for account.");
            // Re-throwing the exception to the caller (Main)
            throw e;
        } finally {
            System.out.println("Transaction attempt completed.");
        }
    }
}
