public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("ACC1001", 1000);
        ATM atm = new ATM();

        // Successful withdrawal
        try {
            atm.processWithdrawal(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }

        System.out.println("----");

        // Failed withdrawal
        try {
            atm.processWithdrawal(account, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }
    }
}

// Why do we re-throw the exception in ATM instead of just catching it? What's the benefit of exception chaining?
// We rethrow the exception so the caller can make the correct business decision, while the ATM layer can log and still guarantee cleanup in finally.
// Exception chaining preserves the root cause and stack trace while adding higher-level context.
