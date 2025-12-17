public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment type: CreditCard, amount: " + amount);
        return true;
    }
}
