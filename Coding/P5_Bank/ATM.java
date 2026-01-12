package P5_Bank;

/*
    Q: Why do we re-throw the exception in ATM instead of just catching it? What's the benefit of exception chaining?
    A: The ATM layer re-throws the exception so that it can log the error while still allowing the exception to propagate
    to higher layers, where the final decision can be made. This prevents the exception from being swallowed, preserves
    clear separation of responsibilities between layers, and supports exception chaining, which makes debugging and
    maintenance easier.
 */
public class ATM {
    public static void processWithdrawal(BankAccount account, double amount) throws Exception{
        try{
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            System.out.println("Transaction attempt completed");
        }
    }
}
