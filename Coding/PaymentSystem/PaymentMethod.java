package Coding.PaymentSystem;

public interface PaymentMethod {
    public abstract boolean processPayment(double amount);

    default void printReceipt() {
        System.out.println("Payment processed successfully.");
    }
}
