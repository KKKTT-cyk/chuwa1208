public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("001-123-4567", 10000);
        ATM atm = new ATM();

        System.out.println("Test1 - Success");
        try {
            atm.processWithdraw(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught: " + e.getMessage());
        }


        System.out.println("\nTest2 - Failed");
        try {
            atm.processWithdraw(account, 10000000);

        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught: " + e.getMessage());
        }
    }
}
