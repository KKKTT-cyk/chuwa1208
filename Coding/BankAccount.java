public class BankAccount{
    // private fields
    private String accountNumber;
    private double balance;

    // paramerters constructor
    public BankAccount (String accountNumber, double balance){
        this.accountNumber = accountNumber;
        if (balance >= 0){
            this.balance = balance;
        }else{
            this.balance = 0;
            System.out.println("Balance cannot be negative");
        }
    }

    // get account and balance
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    // setter
    public void setBalance(double balance){
        if(balance >= 0){
            this.balance = balance;
        }else{
            System.out.println("Balance cannot be negative");
        }
    }
    // deposit method
    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
        }else {
            System.out.println("Deposit cannot be negative");
        }
    }
    // withdraw method
    public  void withdraw(double amount){
        if (amount > 0 && balance >= amount){
            balance -= amount;
        }else {
            System.out.println("Invalid withdraw amount");
        }
    }
}