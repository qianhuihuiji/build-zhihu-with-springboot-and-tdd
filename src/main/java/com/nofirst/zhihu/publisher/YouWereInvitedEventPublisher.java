package com.nofirst.zhihu.publisher;

import com.nofirst.zhihu.event.YouWereInvitedEvent;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class YouWereInvitedEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public YouWereInvitedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(Question question, User user) {
        YouWereInvitedEvent loginEvent = new YouWereInvitedEvent(question, user);
        applicationEventPublisher.publishEvent(loginEvent);
    }
}
