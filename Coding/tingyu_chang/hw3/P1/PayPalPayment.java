package p1;

/*
- Create two implementation classes: `CreditCardPayment` and `PayPalPayment`
- Each implementation class should:
  - Implement `processPayment` (print payment type and amount, return true)
  - Optionally override `printReceipt`
 */

public class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of: $" + amount);
        return true;
    }
    // Uses the default printReceipt() from the Interface
    // choose not to override printReceipt() here.
    // It will automatically use the default implementation from the Interface.
}