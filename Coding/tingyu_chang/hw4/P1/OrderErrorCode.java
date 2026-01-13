package p1;

/*
- Create an enum `OrderErrorCode` implementing `IErrorCode`:
  - `ORDER_NOT_FOUND(1001, "Order not found")`
  - `ORDER_CANCELLED(1002, "Order already cancelled")`
  - `INSUFFICIENT_STOCK(1003, "Insufficient stock")`
 */

public enum OrderErrorCode implements IErrorCode {
    // Enum constants acting as error definitions
    ORDER_NOT_FOUND(1001, "Order not found"),
    ORDER_CANCELLED(1002, "Order already cancelled"),
    INSUFFICIENT_STOCK(1003, "Insufficient stock");

    private long code;
    private String message;

    // Private constructor (Standard for Enums)
    private OrderErrorCode(long code, String message) {
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
