public class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment type: PayPal, amount: " + amount);
        return true;
    }
}
