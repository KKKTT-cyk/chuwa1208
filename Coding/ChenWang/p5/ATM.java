public class ATM {
    public void processWithdrawal(BankAccount account, double amount) throws InsufficientBalanceException {
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println("ATM Log: Failed withdrawal.");
            System.out.println("Details: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Transaction attempt completed.");
        }
    }
}
