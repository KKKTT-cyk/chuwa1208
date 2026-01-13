package p1;

/*
- Create custom exception `OrderException` extending `RuntimeException`:
  - `private IErrorCode errorCode`
  - Constructor accepting `IErrorCode`
  - `public IErrorCode getErrorCode()`
 */

// Custom Unchecked Exception
public class OrderException extends RuntimeException {

    private IErrorCode errorCode;

    public OrderException(IErrorCode errorCode) {
        // Pass the message to the parent RuntimeException
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
