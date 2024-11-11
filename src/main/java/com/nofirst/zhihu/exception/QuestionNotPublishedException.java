package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

public class QuestionNotPublishedException extends ApiException {

    public QuestionNotPublishedException() {
        super(ResultCode.FAILED, "question not publish");
    }
}
