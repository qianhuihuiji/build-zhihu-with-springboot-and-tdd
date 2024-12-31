package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

/**
 * The type Question not published exception.
 */
public class QuestionNotPublishedException extends ApiException {

    /**
     * Instantiates a new Question not published exception.
     */
    public QuestionNotPublishedException() {
        super(ResultCode.FAILED, "question not publish");
    }
}
