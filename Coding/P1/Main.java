package P1;

public class Main {
    public static void main(String[] args) {
        CreditCardPayment c = new CreditCardPayment();
        c.processPayment(100);
        c.printReceipt();
        PayPalPayment p = new PayPalPayment();
        p.processPayment(250.5);
    }
}
