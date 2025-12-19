package P5_Bank;

public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public BankAccount(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void withdraw(double amount) throws InsufficientBalanceException{
        if(balance < amount) {
            throw new InsufficientBalanceException(balance, amount);
        }

        balance -= amount;
        System.out.println("Successfully withdraw " + amount);
    }
}
