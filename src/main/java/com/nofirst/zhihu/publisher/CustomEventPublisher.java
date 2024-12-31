package com.nofirst.zhihu.publisher;

import com.nofirst.zhihu.event.PostAnswerEvent;
import com.nofirst.zhihu.event.PostCommentEvent;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.Question;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * The type Custom event publisher.
 */
@Service
public class CustomEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Instantiates a new Custom event publisher.
     *
     * @param applicationEventPublisher the application event publisher
     */
    public CustomEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Fire publish question event.
     *
     * @param question the question
     */
    public void firePublishQuestionEvent(Question question) {
        PublishQuestionEvent publishQuestionEvent = new PublishQuestionEvent(question);
        applicationEventPublisher.publishEvent(publishQuestionEvent);
    }

    /**
     * Fire post answer event.
     *
     * @param answer the answer
     * @param userId the user id
     */
    public void firePostAnswerEvent(Answer answer, Integer userId) {
        PostAnswerEvent postAnswerEvent = new PostAnswerEvent(answer, userId);
        applicationEventPublisher.publishEvent(postAnswerEvent);
    }

    /**
     * Fire post comment event.
     *
     * @param comment the comment
     */
    public void firePostCommentEvent(Comment comment) {
        PostCommentEvent postCommentEvent = new PostCommentEvent(comment);
        applicationEventPublisher.publishEvent(postCommentEvent);
    }
}
