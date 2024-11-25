package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class YouWereInvitedEvent extends ApplicationEvent {

    @Getter
    private final Question question;
    @Getter
    private final User user;


    public YouWereInvitedEvent(Question question, User user) {
        super(question);
        this.question = question;
        this.user = user;
    }
}
