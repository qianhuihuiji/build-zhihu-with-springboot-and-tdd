package com.nofirst.zhihu.exception;

import com.nofirst.zhihu.common.ResultCode;

public class AnswerNotExistedException extends ApiException {

    public AnswerNotExistedException() {
        super(ResultCode.FAILED, "answer not exist");
    }
}
