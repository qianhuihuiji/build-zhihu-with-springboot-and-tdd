package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentFactory {

    public static Comment create(Integer commentedId, String commentedType) {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setCommentedId(commentedId);
        comment.setCommentedType(commentedType);
        Date now = new Date();
        comment.setCreatedAt(now);
        comment.setUpdatedAt(now);
        comment.setContent("just a comment");

        return comment;
    }

    public static List<Comment> createBatch(Integer times, Integer commentedId, String commentedType) {
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            comments.add(create(commentedId, commentedType));
        }

        return comments;
    }

}
