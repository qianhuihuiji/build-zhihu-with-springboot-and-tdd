package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Comment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PostCommentEvent extends ApplicationEvent {

    @Getter
    private final Comment comment;

    public PostCommentEvent(Comment comment) {
        super(comment);
        this.comment = comment;
    }
}
