package com.github.Xujia118;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("SAV-101", 1000.0);
        ATM myAtm = new ATM();

        // 1. Test Successful Withdrawal
        System.out.println("--- Attempting $500 withdrawal ---");
        try {
            myAtm.processWithdrawal(myAccount, 500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }

        System.out.println("\n--- Attempting $1500 withdrawal ---");
        // 2. Test Failed Withdrawal
        try {
            myAtm.processWithdrawal(myAccount, 1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught in Main: " + e.getMessage());
        }
    }

    /**
     * Why re-throw in the ATM?
     * In a real-world system, responsibilities are split.
     * The ATM needs to catch the exception to perform "local" actions (like ejecting the card or logging a technical log), but it shouldn't "hide" the error.
     * By re-throwing, it informs the User Interface (Main) that the transaction failed so the UI can show a proper error message to the customer.

     * The Benefit of Exception Chaining?
     * While we didn't use initCause() or a wrapped constructor here, exception chaining allows you to wrap a low-level error (like a SQLException)
     * into a high-level error (like BankingServiceException).
     * Abstraction: It hides implementation details from the user.
     * Traceability: It preserves the original stack trace so developers can see exactly where the chain of failure started.
     */
}