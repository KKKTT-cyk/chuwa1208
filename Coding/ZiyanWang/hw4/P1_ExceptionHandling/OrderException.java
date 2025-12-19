public class OrderException extends RuntimeException {

    private final IErrorCode errorCode;

    public OrderException(IErrorCode errorCode) {
        super(errorCode.getMessage()); // Exception message
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}