public class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        return true;
    }

    @Override
    public void printReceipt()
    {
        System.out.println("PayPal payment receipt printed");
    }
}
