import java.util.*;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC001", 1000.00);
        ATM atm = new ATM();

        try {
            atm.processWithdrawal(account, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught: " + e.getMessage());
        }

        try {
            atm.processWithdrawal(account, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Main caught: " + e.getMessage());
        }
    }
}
