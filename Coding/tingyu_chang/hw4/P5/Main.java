package p5;

/*
 In `Main`:
  - Create a BankAccount with balance 1000
  - Test successful withdrawal (500)
  - Test failed withdrawal (1500) and catch the exception
  - Observe that finally block executes in both cases
- Think: Why do we re-throw the exception in ATM instead of just catching it? What's the benefit of exception chaining?

 */

public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("ACC-123", 1000.00);
        ATM atm = new ATM();

        System.out.println("--- Test 1: Successful Withdrawal ---");
        try {
            atm.processWithdrawal(myAccount, 500.00);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main: Handled error -> " + e.getMessage());
        }

        System.out.println("--- Test 2: Failed Withdrawal ---");
        try {
            atm.processWithdrawal(myAccount, 1500.00);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main: Handled error -> " + e.getMessage());
        }
    }
}

/* THINK: Why re-throw the exception?
   - Separation of Concerns: The ATM (Service Layer) needs to log the error for audit purposes,
     but it cannot "fix" the lack of money.
   - Notification: By re-throwing, we ensure the UI layer (Main) is aware the transaction failed
     so it can display an error message to the user.
   - Exception Chaining: Ideally, you might wrap this in a "TransactionFailedException" to hide
     internal details, but simply re-throwing preserves the original cause.
*/


