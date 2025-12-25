public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        // Validate withdrawal amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        // Check if sufficient balance exists
        if (amount > balance) {
            // Throw custom exception with current state information
            throw new InsufficientBalanceException(balance, amount);
        }

        // Perform withdrawal
        balance -= amount;
        System.out.printf("Withdrawal successful: $%.2f withdrawn from account %s\n",
                         amount, accountId);
        System.out.printf("New balance: $%.2f\n", balance);
    }

    // Getters
    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account[ID=%s, Balance=$%.2f]", accountId, balance);
    }
}
