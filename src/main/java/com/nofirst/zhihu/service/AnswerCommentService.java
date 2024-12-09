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

    void comment(Integer answerId, CommentDto commentDto, AccountUser accountUser);

    PageInfo<CommentVo> index(Integer answerId, Integer pageIndex, Integer pageSize);

}
