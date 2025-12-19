package p5;

public class Main {
    public static void main(String[] args) {
        // Create a BankAccount with balance 1000
        BankAccount account = new BankAccount("BOA1244", 1000);
        ATM atm = new ATM();

        // Test successful withdrawal (500)
        System.out.println("==Test successful withdrawal (500)==");
        try {
            atm.processWithdrawal(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        // Test failed withdrawal (1500) and catch the exception
        System.out.println("==Test failed withdrawal (1500)==");
        try {
            atm.processWithdrawal(account, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    /* Think: Why do we re-throw the exception in ATM instead of just
    * catching it?
    * Ans: Because throwing in the ATM layer allows the error to be
    * handled at a higher level while still performing local actions
    * (such as logging taht we are doing here)
    *
    *
    * Think: What's the benefit of exception chaining?
    * Ans: It preserves the original exception context
    * and ensures that important error information is not lost
    * */
}
