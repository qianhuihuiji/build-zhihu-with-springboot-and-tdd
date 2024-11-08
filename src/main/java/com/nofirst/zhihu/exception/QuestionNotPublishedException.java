package com.nofirst.zhihu.exception;

public class QuestionNotPublishedException extends RuntimeException {

    public QuestionNotPublishedException() {
        super("question not publish");
    }
}
