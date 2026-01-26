package P1;
/*
Create custom exception OrderException extending RuntimeException:
    private IErrorCode errorCode
    Constructor accepting IErrorCode
    public IErrorCode getErrorCode()
 */
public class OrderException extends RuntimeException{
    private IErrorCode errorCode;
    public OrderException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
