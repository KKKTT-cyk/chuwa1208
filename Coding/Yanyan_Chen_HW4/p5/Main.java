public class Main {
    public static void main(String[] args) {
        System.out.println("BANK WITHDRAWAL SYSTEM - Exception Handling Demo");
        // Create a bank account with initial balance
        BankAccount account = new BankAccount("ACC-12345", 1000.00);
        System.out.println("Account created: " + account);

        // Create an ATM
        ATM atm = new ATM("ATM-001");

        // Test Case 1: Successful Withdrawal
        System.out.println("\n TEST CASE 1: Successful Withdrawal ($500)\n ");
        
        try {
            atm.processWithdrawal(account, 500.00);
            System.out.println("Transaction completed successfully");
            System.out.println("Updated account: " + account);
            
        } catch (InsufficientBalanceException e) {
            // This won't execute for successful withdrawal
            System.err.println("Transaction failed - " + e.getMessage());
        }

        // Test Case 2: Failed Withdrawal (Insufficient Balance)
        System.out.println("\n TEST CASE 2: Failed Withdrawal ($1500)\n ");
        
        try {
            atm.processWithdrawal(account, 1500.00);
            System.out.println("Transaction completed successfully");

        } catch (InsufficientBalanceException e) {
            System.out.println("\n[MAIN ERROR HANDLER]");  // 改用 System.out
            System.out.println("Transaction failed in Main");
            System.out.println("Reason: " + e.getMessage());
            System.out.println("Account balance remains: $" + account.getBalance());
        }
    }
}
