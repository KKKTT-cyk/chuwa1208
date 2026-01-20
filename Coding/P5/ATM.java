package P5;
/*
Create ATM class:
    public void processWithdrawal(BankAccount account, double amount)
    Try to call account.withdraw(amount)
    Catch InsufficientBalanceException:
    Log the error with details
    Re-throw the exception using throw e
    Use finally block to print "Transaction attempt completed"
 */
public class ATM {
    public void processWithdrawal(BankAccount account, double amount) throws InsufficientBalanceException{
        try{
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println("[Log] withdraw failed for account:" + account.getAccountId() + "Amount: "
                    + amount + " reason: " + e.getMessage());
            throw e;
        }finally {
            System.out.println("Transaction attempt completed");
        }
    }
}

