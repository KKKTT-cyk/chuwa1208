package Coding.PaymentSystem;

public class PayPalPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }
        System.out.println("Processing PayPal payment of $" + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("PayPal payment processed successfully.");
    }
}
