package P1;

public class CreditCardPayment implements PaymentMethod{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("CreditCard" + amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("Credit-Receipt");
    }
}
