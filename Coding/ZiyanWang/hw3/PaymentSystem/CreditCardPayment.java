public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        return true;
    }

    // Optional override
    @Override
    public void printReceipt() {
        System.out.println("Credit card payment receipt generated");
    }
}
