package com.github.Xujia118;

public class OrderException extends RuntimeException {
    private final IErrorCode errorCode;

    public OrderException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
