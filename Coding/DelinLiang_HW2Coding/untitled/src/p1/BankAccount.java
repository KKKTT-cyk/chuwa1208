package p1;

public class BankAccount {
    // Private fields: accountNumber (String), balance (double)
    private String accountNumber;
    private double balance;

    // A parameterized constructor
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getter and setter methods with validation (balance cannot be negative)

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance amount cannot be negative");
        }
        this.balance = balance;
    }

    // A method deposit(double amount) and withdraw(double amount)
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount cannot be negative");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance, cannot withdraw");
        }
        balance -= amount;
    }
}
