public class Main {
    public static void main(String[] args) {
        PaymentMethod creditCardPayment = new CreditCardPayment();
        if (creditCardPayment.processPayment(100.0)) {
            creditCardPayment.printReceipt();
        }

        PaymentMethod paypalPayment = new PayPalPayment();
        if (paypalPayment.processPayment(250.5)) {
            paypalPayment.printReceipt();
        }
    }
}