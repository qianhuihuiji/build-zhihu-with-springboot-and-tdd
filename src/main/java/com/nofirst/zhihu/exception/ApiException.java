package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.IErrorCode;

/**
 * 自定义API异常
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    /**
     * Instantiates a new Api exception.
     *
     * @param errorCode the error code
     */
    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new Api exception.
     *
     * @param errorCode the error code
     * @param message   the message
     */
    public ApiException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorCode.setMessage(message);
    }

    /**
     * Instantiates a new Api exception.
     *
     * @param message the message
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Api exception.
     *
     * @param cause the cause
     */
    public ApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
