package src.P1_interface;

public class Main {
    public static void main(String[] args) {
        PaymentMethod credit_card = new CreditCardPayment();
        PaymentMethod paypal = new PayPalPayment();
        credit_card.processPayment(100.0);
        credit_card.processPayment(250.5);
        paypal.processPayment(100.0);
        paypal.processPayment(250.5);
    }
}
