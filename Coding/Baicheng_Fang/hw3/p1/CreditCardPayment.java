public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Credit card paid: " + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("Credid card receipt");
    }

}
