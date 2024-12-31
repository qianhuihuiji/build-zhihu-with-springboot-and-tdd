package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

/**
 * The type Answer not existed exception.
 */
public class AnswerNotExistedException extends ApiException {

    /**
     * Instantiates a new Answer not existed exception.
     */
    public AnswerNotExistedException() {
        super(ResultCode.FAILED, "answer not exist");
    }
}
