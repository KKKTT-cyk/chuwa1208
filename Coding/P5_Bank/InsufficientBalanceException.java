package P5_Bank;

public class InsufficientBalanceException extends Exception {
    private double balance, requestedAmount;
    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(double balance, double requestedAmount) {
        super("Insufficient Balance Exception: " +
                "Balance = " + balance +
                "requested Amount = " + requestedAmount);
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String getMessage() {
        return "Insufficient Balance Exception: " +
                "Balance = " + balance +
                "requested Amount = " + requestedAmount;
    }
}
