package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.CommentMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * The type Question subscribe service.
 */
@Service
@AllArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService {

    private final QuestionMapper questionMapper;
    private final CommentMapper commentMapper;

    @Override
    public void comment(Integer questionId, CommentDto commentDto, AccountUser accountUser) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }

        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException();
        }
        Comment comment = new Comment();
        comment.setUserId(accountUser.getUserId());
        comment.setContent(commentDto.getContent());
        comment.setCommentedId(questionId);
        comment.setCommentedType(Question.class.getSimpleName());
        Date date = new Date();
        comment.setCreatedAt(date);
        comment.setUpdatedAt(date);
        commentMapper.insert(comment);
    }
}
