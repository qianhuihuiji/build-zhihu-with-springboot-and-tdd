package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * The type Publish question event.
 */
public class PublishQuestionEvent extends ApplicationEvent {

    @Getter
    private final Question question;


    /**
     * Instantiates a new Publish question event.
     *
     * @param question the question
     */
    public PublishQuestionEvent(Question question) {
        super(question);
        this.question = question;
    }
}
