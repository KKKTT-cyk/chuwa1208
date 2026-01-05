package p1;

/*
- Create an `IErrorCode` interface with:
  - `long getCode()`
  - `String getMessage()`
 */

// Interface defining the contract for error codes
public interface IErrorCode {
    long getCode();
    String getMessage();
}
