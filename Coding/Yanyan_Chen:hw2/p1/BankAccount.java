public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

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
            throw new IllegalArgumentException("Error: Balance amount must be positive.");
        }

        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Successfully Deposited: $" + amount + ". New balance: $" + this.balance);
            return true;
        } else {
            System.out.println("Error: Deposit amount must be positive.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return false;
        }

        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Successfully Withdrew: $" + amount + ". New balance: $" + this.balance);
            return true;
        } else {
            System.out.println("Error: Insufficient funds. Available balance: $" + this.balance);
            return false;
        }
    }
}
