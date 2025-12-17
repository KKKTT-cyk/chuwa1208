public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit payment of $" + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("Credit payment receipt printed");
    }
}
