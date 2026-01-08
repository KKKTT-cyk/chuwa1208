package p1;

public class Main {
    public static void main(String[] args) {
        // Test both payment methods in Main class with amounts 100.0 and 250.5
        PaymentMethod cc = new CreditCardPayment();
        cc.processPayment(100.0);
        cc.printReceipt();

        cc.processPayment(250.0);
        cc.printReceipt();

        PaymentMethod pp = new PayPalPayment();
        pp.processPayment(100.0);
        pp.printReceipt();

        pp.processPayment(250.5);
        pp.printReceipt();
    }
}
