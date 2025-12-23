package p5;

/*
- Create `ATM` class:
  - `public void processWithdrawal(BankAccount account, double amount)`
  - Try to call `account.withdraw(amount)`
  - Catch `InsufficientBalanceException`:
    - Log the error with details
    - Re-throw the exception using `throw e`
  - Use finally block to print "Transaction attempt completed"
 */
public class ATM {

    // This method catches the error, logs it, and then RE-THROWS it
    // so the caller (Main) knows something went wrong.
    public void processWithdrawal(BankAccount account, double amount) throws InsufficientBalanceException {
        try {
            System.out.println("ATM: Requesting withdrawal of $" + amount + " from " + account.getAccountId());
            account.withdraw(amount);

        } catch (InsufficientBalanceException e) {
            // Log the error locally
            System.out.println("ATM LOG: Transaction denied. Reason: " + e.getMessage());

            // Re-throw the exception to notify the main system
            throw e;

        } finally {
            // This executes whether the withdrawal succeeded OR failed [cite: 21-24]
            System.out.println("ATM: Transaction attempt completed (Card ejected).\n");
        }
    }
}

