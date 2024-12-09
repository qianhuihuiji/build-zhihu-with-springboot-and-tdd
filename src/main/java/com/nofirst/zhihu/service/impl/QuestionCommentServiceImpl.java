package com.nofirst.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.CommentMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.CommentExample;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.model.vo.CommentVo;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Question subscribe service.
 */
@Service
@AllArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService {

    private final QuestionMapper questionMapper;
    private final CommentMapper commentMapper;

    private CustomEventPublisher customEventPublisher;

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

        customEventPublisher.firePostCommentEvent(comment);
    }

    @Override
    public PageInfo<CommentVo> index(Integer questionId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andCommentedIdEqualTo(questionId);
        criteria.andCommentedTypeEqualTo(Question.class.getSimpleName());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        List<CommentVo> result = new ArrayList<>();
        for (Comment comment : comments) {
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setCommentedId(comment.getCommentedId());
            commentVo.setContent(comment.getContent());
            commentVo.setCreateTime(comment.getCreatedAt());

            result.add(commentVo);
        }
        PageInfo<CommentVo> pageResult = new PageInfo<>();
        pageResult.setTotal(commentPageInfo.getTotal());
        pageResult.setPageNum(commentPageInfo.getPageNum());
        pageResult.setPageSize(commentPageInfo.getPageSize());
        pageResult.setList(result);
        return pageResult;
    }
}
