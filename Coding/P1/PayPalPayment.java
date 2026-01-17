package P1;

public class PayPalPayment implements PaymentMethod{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayPal" + amount);
        return true;
    }

    @Override
    public void printReceipt() {
//        PaymentMethod.super.printReceipt();
        System.out.println("PayPal-Receipt");
    }
}
