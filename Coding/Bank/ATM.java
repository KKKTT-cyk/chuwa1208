package Coding.Bank;

/**
 * Question: Why do we need to throw the exception again?
 * A: Because we want to let the caller know the error.
 * And the caller can handle the error differently.
 */
public class ATM {
    public void processWithdrawal(BankAccount account, double amount)
            throws InsufficientBalanceException {

        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {

            // Logging (ATM-level concern)
            System.out.println(
                    "ATM LOG: Account=" + account.getAccountId() +
                            ", Error=" + e.getMessage());

            // Re-throw to caller
            throw e;

        } finally {
            System.out.println("Transaction attempt completed");
        }
    }
}
