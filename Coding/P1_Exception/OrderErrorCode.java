package P1_Exception;

public enum OrderErrorCode implements IErrorCode{
    ORDER_NOT_FOUND(1001, "Order not found"),
    ORDER_CANCELLED(1002,"Order already cancelled"),
    INSUFFICIENT_STOCK(1003,"Insufficient stock");

    private final long code;
    private final String msg;


    private OrderErrorCode(long code, String msg){
        this.code = code;
        this.msg = msg;
    }


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessgae() {
        return msg;
    }


}
