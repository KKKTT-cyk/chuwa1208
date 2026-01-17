package P1;

public interface PaymentMethod {
    // abstract method
    boolean processPayment(double amount);

    // default method
    default void printReceipt(){
        System.out.println("Payment processed successfully");
    }
}
