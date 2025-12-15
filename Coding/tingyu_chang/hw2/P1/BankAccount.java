public class BankAccount {
    // 1. Private fields for Encapsulation
    private String accountNumber;
    private double balance;

    // 2. Parameterized Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        // Validate initial balance
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Error: Initial balance cannot be negative. Setting to 0.");
            this.balance = 0;
        }
    }

    // 3. Getter and Setter methods with Validation (balance cannot be negative)
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
        // Validation: balance cannot be negative
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Error: Balance cannot be negative.");
        }
    }

    // 4. Methods for Deposit and Withdraw
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Error: Invalid withdrawal amount or insufficient funds.");
        }
    }
}
