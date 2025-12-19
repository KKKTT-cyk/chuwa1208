public class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;

    public InsufficientBalanceException(double balance, double requestedAmount) {
        super(); // Call parent Exception constructor
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String getMessage() {
        double shortfall = requestedAmount - balance;
        return String.format(
            "Insufficient balance: Requested $%.2f, but only $%.2f available. Short by $%.2f",
            requestedAmount, balance, shortfall
        );
    }

    // Getters for accessing exception details
    public double getBalance() {
        return balance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
