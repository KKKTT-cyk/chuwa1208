package P1_Exception;

public class OrderException extends RuntimeException {

    /*
        Q: Why use an interface instead of directly using the enum?
        A: decoupling from the concrete enum class, can easily extend this exception to other domains
        By define new enum ErrorCode class
     */
    private IErrorCode errorCode;

    public OrderException(String message) {
        super(message);
    }

    public OrderException(IErrorCode errorCode) {
        super(errorCode.getMessgae());
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
