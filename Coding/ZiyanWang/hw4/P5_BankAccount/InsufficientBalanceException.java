public class InsufficientBalanceException extends Exception {

    private final double balance;
    private final double requestedAmount;

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
        return "Insufficient balance. Current balance: " + balance
                + ", Requested amount: " + requestedAmount;
    }
}
