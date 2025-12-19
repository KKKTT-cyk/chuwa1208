public class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;
    public InsufficientBalanceException(double balance, double requestedAmount) {
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public double getBalance() {
        return balance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    @Override
    public String getMessage() {
        return String.format(
                "Insufficient balance. Current balance: %.2f, Requested: %.2f",
                balance, requestedAmount
        );
    }
}
