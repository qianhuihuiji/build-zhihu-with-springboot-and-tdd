package com.nofirst.zhihu.service;

import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionCommentService
 *
 * @author nofirst
 */
public interface QuestionCommentService {

    void comment(Integer questionId, CommentDto commentDto, AccountUser accountUser);

}
