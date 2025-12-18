package p1;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Payment System ---");

        // 1. Credit Card (Custom receipt)
        PaymentMethod cc = new CreditCardPayment();
        cc.processPayment(100.0);
        cc.printReceipt();

        System.out.println();

        // 2. PayPal (Default receipt)
        PaymentMethod pp = new PayPalPayment();
        pp.processPayment(250.5);
        pp.printReceipt();
    }
}