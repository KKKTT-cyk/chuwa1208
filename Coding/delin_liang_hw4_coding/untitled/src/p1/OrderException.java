package p1;

public class OrderException extends RuntimeException {
    private IErrorCode errorCode;

    // Constructor accepting IErrorCode
    public OrderException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
