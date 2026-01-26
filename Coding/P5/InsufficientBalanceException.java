package P5;
/*
Create custom exception InsufficientBalanceException extending Exception:
    private double balance
    private double requestedAmount
    Constructor accepting both values
    Override getMessage() to return formatted message
 */
public class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;

    // Constructor both values
    public InsufficientBalanceException(double balance, double requestedAmount) {
        this.requestedAmount = requestedAmount;
        this.balance = balance;
    }
    @Override
    public String getMessage(){
        return String.format("Insufficient balance: current=%2f, requested=%2f", balance, requestedAmount);
    }
}
