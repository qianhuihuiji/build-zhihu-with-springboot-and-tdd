package com.nofirst.zhihu.publisher;

import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.model.Question;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void addPublishQuestionEvent(Question question) {
        PublishQuestionEvent publishQuestionEvent = new PublishQuestionEvent(question);
        applicationEventPublisher.publishEvent(publishQuestionEvent);
    }
}
