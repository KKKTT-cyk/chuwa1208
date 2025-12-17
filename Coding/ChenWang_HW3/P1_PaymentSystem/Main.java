public class Main {
    public static void main(String[] args) {
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        PayPalPayment payPalPayment = new PayPalPayment();

        creditCardPayment.processPayment(100.0);
        creditCardPayment.processPayment(250.5);

        payPalPayment.processPayment(100.0);
        payPalPayment.processPayment(250.5);
    }
}
