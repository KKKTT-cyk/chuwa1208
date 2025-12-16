package src.P1_interface;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("** Payment type: Credit Card");
        System.out.println("** Payment amount: " + amount);
        return true;
    }
}
