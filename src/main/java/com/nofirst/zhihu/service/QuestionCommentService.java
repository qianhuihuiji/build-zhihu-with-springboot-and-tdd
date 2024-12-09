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

    void comment(Integer questionId, CommentDto commentDto, AccountUser accountUser);

    PageInfo<CommentVo> index(Integer questionId, Integer pageIndex, Integer pageSize);
}
