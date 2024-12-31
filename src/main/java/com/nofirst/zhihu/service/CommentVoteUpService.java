package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020 -08-24 22:34
 */
public interface CommentVoteUpService {

    /**
     * Store.
     *
     * @param commentId   the comment id
     * @param accountUser the account user
     */
    void store(Integer commentId, AccountUser accountUser);

    /**
     * Destroy.
     *
     * @param commentId   the comment id
     * @param accountUser the account user
     */
    void destroy(Integer commentId, AccountUser accountUser);
}
