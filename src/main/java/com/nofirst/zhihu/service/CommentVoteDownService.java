package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface CommentVoteDownService {

    void store(Integer commentId, AccountUser accountUser);

    void destroy(Integer commentId, AccountUser accountUser);
}
