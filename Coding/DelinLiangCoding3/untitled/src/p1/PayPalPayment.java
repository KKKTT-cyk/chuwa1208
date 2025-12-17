package p1;

public class PayPalPayment implements PaymentMethod {
    private double lastAmount;
    // Implement processPayment (print payment type and amount, return true)
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing: PayPal payment - $" + amount);
        lastAmount = amount;
        return true;
    }

    // Optionally override printReceipt
    @Override
    public void printReceipt() {
        System.out.println("-----------------------------");
        System.out.println("---PayPal Payment Receipt----");
        System.out.println("--------------------$" + lastAmount);
        System.out.println("-----------------------------");
    }
}
