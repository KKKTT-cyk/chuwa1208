package Coding.MultiLevelExceptionSystem;

/**
 * Think: Why we use interface instead of directly using enum?
 * A: Because we can use interface to implement multiple different enum types.
 * For example:
 * public enum OrderErrorCode implements IErrorCode {
 * ORDER_NOT_FOUND(1001, "Order not found"),
 * ORDER_CANCELLED(1002, "Order already cancelled"),
 * INSUFFICIENT_STOCK(1003, "Insufficient stock");
 * }
 */
public enum OrderErrorCode implements IErrorCode {
    ORDER_NOT_FOUND(1001, "Order not found"),
    ORDER_CANCELLED(1002, "Order already cancelled"),
    INSUFFICIENT_STOCK(1003, "Insufficient stock");

    private final long code;
    private final String message;

    OrderErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
