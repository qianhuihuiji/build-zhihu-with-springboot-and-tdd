package com.nofirst.zhihu.common;

/**
 * API返回码接口
 * Created by macro on 2019/4/19.
 */
public interface IErrorCode {
    /**
     * 返回码
     *
     * @return the code
     */
    long getCode();

    /**
     * Sets message.
     *
     * @param message the message
     */
    void setMessage(String message);

    /**
     * 返回信息
     *
     * @return the message
     */
    String getMessage();
}
