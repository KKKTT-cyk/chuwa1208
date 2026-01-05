public class ATM {
    public void processWithdraw(BankAccount account, double amount) throws InsufficientBalanceException {
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            System.out.println("Session ended");
        }
    }
}
