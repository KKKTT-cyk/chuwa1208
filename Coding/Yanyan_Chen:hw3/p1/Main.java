public class Main {
    public static void main(String[] args) {

        PaymentMethod creditCard = new CreditCardPayment();
        PaymentMethod payPal = new PayPalPayment();

        creditCard.processPayment(100.0);
        creditCard.printReceipt();

        creditCard.processPayment(250.5);
        creditCard.printReceipt();

        System.out.println();

        payPal.processPayment(100.0);
        payPal.printReceipt();

        payPal.processPayment(250.5);
        payPal.printReceipt();
    }
}
