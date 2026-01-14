public enum OrderErrorCode implements IErrorCode {
    ORDER_NOT_FOUND(1001, "Order not found"),
    ORDER_CANCELLED(1002, "Order already cancelled"),
    INSUFFICIENT_STOCK(1003, "Insufficient stock"),
    ILLEGALARGUMENT(1004, "Illegal Argument");


    private long code;
    private String errorMessage;

    private OrderErrorCode(long code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
