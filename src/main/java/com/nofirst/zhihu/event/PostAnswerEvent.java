package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Answer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * The type Post answer event.
 */
public class PostAnswerEvent extends ApplicationEvent {

    @Getter
    private final Answer answer;
    @Getter
    private final Integer userId;

    /**
     * Instantiates a new Post answer event.
     *
     * @param answer the answer
     * @param userId the user id
     */
    public PostAnswerEvent(Answer answer, Integer userId) {
        super(answer);
        this.answer = answer;
        this.userId = userId;
    }
}
