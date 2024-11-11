package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

public class QuestionNotExistedException extends ApiException {

    public QuestionNotExistedException() {
        super(ResultCode.FAILED, "question not exist");
    }
}
