public class PayPalPayment implements PaymentMethod{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Paypal paid: " + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("Paypal receipt");
    }
}
