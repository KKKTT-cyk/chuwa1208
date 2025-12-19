public class ATM {

    public void processWithdrawal(BankAccount account, double amount) throws InsufficientBalanceException {

        try {
            System.out.println("Processing withdrawal for account: " + account.getAccountId()
                    + ", Amount: " + amount);

            account.withdraw(amount);

        } catch (InsufficientBalanceException e) {
            // Log with details
            System.out.println("ERROR: " + e.getMessage());

            // Re-throw the same exception upward
            throw e;

        } finally {
            System.out.println("Transaction attempt completed");
        }
    }
}
