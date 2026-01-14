package Coding.Bank;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount("ACC123", 1000);
        ATM atm = new ATM();

        // Successful withdrawal
        try {
            atm.processWithdrawal(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught error: " + e.getMessage());
        }

        System.out.println();

        // Failed withdrawal
        try {
            atm.processWithdrawal(account, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught error: " + e.getMessage());
        }
    }
}
