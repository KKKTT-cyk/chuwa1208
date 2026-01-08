package p1;

/*
- Create two implementation classes: `CreditCardPayment` and `PayPalPayment`
- Each implementation class should:
  - Implement `processPayment` (print payment type and amount, return true)
  - Optionally override `printReceipt`
 */

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment of: $" + amount);
        return true;
    }

    // Optionally overriding the default method
    @Override
    public void printReceipt() {
        System.out.println("Receipt sent to user's email.");
    }
}