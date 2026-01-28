package com.chuwa.redbook.exception;

import org.springframework.http.HttpStatus;

/**
 * 无效输入异常 - 当用户提供的数据不合法时抛出
 * 例如：标题为空、内容太短等
 */
public class InvalidInputException extends RuntimeException {
    private HttpStatus status;

    public InvalidInputException(String message) {
        super(message);
        this.status = HttpStatus.UNPROCESSABLE_ENTITY; // 返回 400 错误码
    }

    public HttpStatus getStatus() {
        return status;
    }
}
