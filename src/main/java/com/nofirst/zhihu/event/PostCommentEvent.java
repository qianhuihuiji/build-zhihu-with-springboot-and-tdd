package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Comment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * The type Post comment event.
 */
public class PostCommentEvent extends ApplicationEvent {

    @Getter
    private final Comment comment;

    /**
     * Instantiates a new Post comment event.
     *
     * @param comment the comment
     */
    public PostCommentEvent(Comment comment) {
        super(comment);
        this.comment = comment;
    }
}
