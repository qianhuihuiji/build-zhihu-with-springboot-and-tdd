package com.nofirst.zhihu.service;

import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.security.AccountUser;

/**
 * AnswerCommentService
 *
 * @author nofirst
 */
public interface AnswerCommentService {

    void comment(Integer answerId, CommentDto commentDto, AccountUser accountUser);

}
