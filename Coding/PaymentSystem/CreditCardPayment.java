package Coding.PaymentSystem;

public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }
        System.out.println("Processing credit card payment of $" + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("Credit card Payment processed successfully.");
    }

}
