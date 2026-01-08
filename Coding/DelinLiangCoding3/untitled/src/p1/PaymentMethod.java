package p1;

public interface PaymentMethod {
    // boolean processPayment(double amount) - abstract method
    boolean processPayment(double amount);

    // default void printReceipt() - prints "Payment processed successfully"
    default void printReceipt() {
        System.out.println("Payment processed successfully");
    }
}
