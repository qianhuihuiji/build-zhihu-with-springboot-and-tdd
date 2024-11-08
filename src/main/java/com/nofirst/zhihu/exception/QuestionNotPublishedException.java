package com.nofirst.zhihu.exception;

public class QuestionNotPublishedException extends RuntimeException {

    public QuestionNotPublishedException(String message) {
        super(message);
    }
}
