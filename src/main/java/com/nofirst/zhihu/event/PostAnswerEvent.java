package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Answer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PostAnswerEvent extends ApplicationEvent {

    @Getter
    private final Answer answer;
    @Getter
    private final Integer userId;

    public PostAnswerEvent(Answer answer, Integer userId) {
        super(answer);
        this.answer = answer;
        this.userId = userId;
    }
}
