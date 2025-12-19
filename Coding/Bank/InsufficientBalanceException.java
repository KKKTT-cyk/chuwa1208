package Coding.Bank;

public class InsufficientBalanceException extends Exception {
    private final double balance;
    private final double requestedAmount;

    public InsufficientBalanceException(double balance, double requestedAmount) {
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String getMessage() {
        return "Insufficient balance. Available: " + balance +
                ", Requested: " + requestedAmount;
    }
}
