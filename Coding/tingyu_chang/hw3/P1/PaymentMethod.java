package p1;

/*
- Create a `PaymentMethod` interface with:
  - `boolean processPayment(double amount)` - abstract method
  - `default void printReceipt()` - prints "Payment processed successfully"
 */
public interface PaymentMethod {
    // Abstract method
    boolean processPayment(double amount);

    // Default method
    default void printReceipt() {
        System.out.println("Payment processed successfully");
    }
}