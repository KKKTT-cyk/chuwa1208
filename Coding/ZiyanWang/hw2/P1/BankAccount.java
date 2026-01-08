public class BankAccount {
    //Private fields
    private String accountNumber;
    private double balance;

    // parameterized constructor
    public BankAccount(string accountNumber, double balance) {
        this.accountNumber = accountNumber;
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    // getter for accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    // setter for accountNumber
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // getter for balance
    public double getBalance() {
        return balance;
    }

    // setter for balance
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    // method
    public void deposite(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }
    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (amount > blance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }
}