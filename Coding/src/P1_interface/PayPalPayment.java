package src.P1_interface;

public class PayPalPayment implements PaymentMethod{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("** Payment type: PayPal");
        System.out.println("** Payment amount: " + amount);
        return true;
    }
}
