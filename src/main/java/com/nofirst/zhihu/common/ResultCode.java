package com.nofirst.zhihu.common;

/**
 * API返回码封装类
 */
public enum ResultCode implements IErrorCode {
    /**
     * Success result code.
     */
    SUCCESS(200, "操作成功"),
    /**
     * Failed result code.
     */
    FAILED(500, "操作失败"),
    /**
     * Validate failed result code.
     */
    VALIDATE_FAILED(400, "参数检验失败"),
    /**
     * Unauthorized result code.
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * Forbidden result code.
     */
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
