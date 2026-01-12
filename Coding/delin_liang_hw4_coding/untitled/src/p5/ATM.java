package p5;

public class ATM {
    public void processWithdrawal(BankAccount account, double amount)
            throws InsufficientBalanceException {
        // Try to call account.withdraw(amount)
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) { // Catch InsufficientBalanceException:
            // Log the error with details
            System.out.println("[ATM Error] Withdrawal Failed: " + e.getMessage());

            // Re-throw the exception using throw e
            throw e;
        } finally {
            // Use finally block to print "Transaction attempt completed"
            System.out.println("Transaction attempt completed");
        }
    }
}
