package com.github.Xujia118;

class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;

    public InsufficientBalanceException(double balance, double requestedAmount) {
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String getMessage() {
        return String.format("Withdrawal failed! Current Balance: $%.2f, Requested: $%.2f",
                balance, requestedAmount);
    }
}
