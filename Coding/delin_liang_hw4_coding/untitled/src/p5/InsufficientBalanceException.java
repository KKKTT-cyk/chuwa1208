package p5;

public class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;

    // Constructor accepting both values
    public InsufficientBalanceException(double balance, double requestedAmount) {
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    // Override getMessage() to return formatted message
    @Override
    public String getMessage() {
        return String.format(
                "InsufficientBalanceException: Current balance: %.2f, Requested amount: %.2f",
                balance, requestedAmount
        );
    }
}
