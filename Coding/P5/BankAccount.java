package P5;
/*
Create BankAccount class:
    private String accountId
    private double balance
    Constructor, getters
    public void withdraw(double amount) throws InsufficientBalanceException
    Check if amount > balance, throw exception if true
    Otherwise deduct amount and print success message
 */
public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getter
    public double getBalance(){
        return balance;
    }
    public String getAccountId(){
        return accountId;
    }
    public void withdraw(double amount) throws InsufficientBalanceException{
        if (amount > balance) throw new InsufficientBalanceException(balance, amount);
        balance -= amount;
        System.out.println("Withdraw successfully, Total balance: " + balance);
    }
}
