package src.P1_interface;

public interface PaymentMethod {
    boolean processPayment(double amount);
    default void printReceipt() {
        System.out.println("Payment processed successfully");
    }
}
