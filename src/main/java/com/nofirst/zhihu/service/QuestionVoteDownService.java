package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020 -08-24 22:34
 */
public interface QuestionVoteDownService {

    /**
     * Store.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     */
    void store(Integer questionId, AccountUser accountUser);

    /**
     * Destroy.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     */
    void destroy(Integer questionId, AccountUser accountUser);
}
