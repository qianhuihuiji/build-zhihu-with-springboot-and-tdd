package com.nofirst.zhihu.exception;

public class QuestionNotExistedException extends RuntimeException {

    public QuestionNotExistedException(String message) {
        super(message);
    }
}
