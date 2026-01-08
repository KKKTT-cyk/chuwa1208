public class Main {
    public static void main(String[] args) {
        PaymentMethod card = new CreditCardPayment();
        PaymentMethod paypal = new PayPalPayment();

        card.processPayment(100.0);
        paypal.processPayment(100.0);

        card.processPayment(250.5);
        paypal.processPayment(250.5);
    }
}
