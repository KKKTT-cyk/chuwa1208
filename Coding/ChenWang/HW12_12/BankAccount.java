public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        setBalance(balance);
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
            System.out.println("Balance cannot be negative.");
            this.balance = 0.0;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Deposit amount cannot be negative.");
            return;
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return;
        } else if (amount > balance){
            System.out.println("Withdraw amount cannot be greater than balance.");
            return;
        } else {
            balance -= amount;
        }
    }

 /*
    private double validateBalance(double balance) {
        if (balance < 0) {
            System.out.println("Balance cannot be negative.");
            return 0.0;
        } else {
            return balance;
        }
    }
 */
}
