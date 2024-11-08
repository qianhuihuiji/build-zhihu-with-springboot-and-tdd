package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Answer;

import java.util.Date;

public class AnswerFactory {

    public static Answer createAnswer(Long questionId) {
        Date now = new Date();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setQuestionId(questionId);
        answer.setUserId(1);
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);
        answer.setContent("this is a answer");

        return answer;
    }
}