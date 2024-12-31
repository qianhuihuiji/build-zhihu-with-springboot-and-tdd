package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.model.vo.CommentVo;
import com.nofirst.zhihu.security.AccountUser;

/**
 * AnswerCommentService
 *
 * @author nofirst
 */
public interface AnswerCommentService {

    /**
     * Comment.
     *
     * @param answerId    the answer id
     * @param commentDto  the comment dto
     * @param accountUser the account user
     */
    void comment(Integer answerId, CommentDto commentDto, AccountUser accountUser);

    /**
     * Index page info.
     *
     * @param answerId  the answer id
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the page info
     */
    PageInfo<CommentVo> index(Integer answerId, Integer pageIndex, Integer pageSize);

}
