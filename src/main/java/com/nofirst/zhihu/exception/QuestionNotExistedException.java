package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

/**
 * The type Question not existed exception.
 */
public class QuestionNotExistedException extends ApiException {

    /**
     * Instantiates a new Question not existed exception.
     */
    public QuestionNotExistedException() {
        super(ResultCode.FAILED, "question not exist");
    }
}
