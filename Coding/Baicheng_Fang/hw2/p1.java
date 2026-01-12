public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double value) {
        if (value >= 0) {
            balance += value;
        }
    }

    public void withdraw(double value) {
        if (balance >= value) {
            balance -= value;
        }
    }
}
