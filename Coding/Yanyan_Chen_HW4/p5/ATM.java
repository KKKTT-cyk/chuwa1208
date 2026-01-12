public class ATM {
    private String atmId;

    public ATM(String atmId) {
        this.atmId = atmId;
    }
    public void processWithdrawal(BankAccount account, double amount) 
            throws InsufficientBalanceException {
        
        System.out.printf("\n[ATM %s] Processing withdrawal request...\n", atmId);
        System.out.printf("Account: %s\n", account.getAccountId());
        System.out.printf("Amount: $%.2f\n", amount);
        
        try {
            // Attempt the withdrawal
            account.withdraw(amount);
            System.out.println("[ATM] Transaction approved");

        } catch (InsufficientBalanceException e) {
            System.out.println("\n[ATM ERROR LOG]");
            System.out.println("ATM ID: " + atmId);
            System.out.println("Account: " + account.getAccountId());
            System.out.println("Error: " + e.getMessage());
            System.out.println("Timestamp: " + java.time.LocalDateTime.now());

            throw e;
            
        } finally {
            // Finally block ALWAYS executes, regardless of success or exception
            // Useful for cleanup operations, logging, resource management
            System.out.println("[ATM] Transaction attempt completed");
            System.out.println("─".repeat(50));
        }
    }

    public String getAtmId() {
        return atmId;
    }
}
