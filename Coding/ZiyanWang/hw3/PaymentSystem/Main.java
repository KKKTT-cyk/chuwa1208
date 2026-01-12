public class Main {
    public static void main(String[] args) {

        PaymentMethod creditCard = new CreditCardPayment();
        PaymentMethod paypal = new PayPalPayment();

        creditCard.processPayment(100.0);
        creditCard.printReceipt();

        System.out.println();

        paypal.processPayment(250.5);
        paypal.printReceipt();
    }
}
