package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.model.vo.CommentVo;
import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionCommentService
 *
 * @author nofirst
 */
public interface QuestionCommentService {

    /**
     * Comment.
     *
     * @param questionId  the question id
     * @param commentDto  the comment dto
     * @param accountUser the account user
     */
    void comment(Integer questionId, CommentDto commentDto, AccountUser accountUser);

    /**
     * Index page info.
     *
     * @param questionId the question id
     * @param pageIndex  the page index
     * @param pageSize   the page size
     * @return the page info
     */
    PageInfo<CommentVo> index(Integer questionId, Integer pageIndex, Integer pageSize);
}
