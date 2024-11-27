package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PublishQuestionEvent extends ApplicationEvent {

    @Getter
    private final Question question;


    public PublishQuestionEvent(Question question) {
        super(question);
        this.question = question;
    }
}
