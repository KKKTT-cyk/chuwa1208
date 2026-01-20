package P5;
/*
In Main:
    Create a BankAccount with balance 1000
    Test successful withdrawal (500)
    Test failed withdrawal (1500) and catch the exception
    Observe that finally block executes in both cases
    Think: Why do we re-throw the exception in ATM instead of just catching it? What's the benefit of exception
 */
public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("100-111", 1000);
        ATM atm = new ATM();
        // withdrawal 500
        try {
            atm.processWithdrawal(bankAccount, 500);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        System.out.println("------------");

        try {
            atm.processWithdrawal(bankAccount, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
