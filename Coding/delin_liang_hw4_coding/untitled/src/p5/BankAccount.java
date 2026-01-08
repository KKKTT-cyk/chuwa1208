package p5;

public class BankAccount {
    private String accountId;
    private double balance;

    // Constructor, getters
    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        // Check if amount > balance, throw exception if true
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }

        // Otherwise deduct amount and print success message
        balance -= amount;
        System.out.printf("Withdrawal successful! New balance: %.2f%n", balance);
    }
}
