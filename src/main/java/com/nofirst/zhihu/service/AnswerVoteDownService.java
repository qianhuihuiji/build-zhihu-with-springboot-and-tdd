package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020 -08-24 22:34
 */
public interface AnswerVoteDownService {

    /**
     * Store.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     */
    void store(Integer answerId, AccountUser accountUser);

    /**
     * Destroy.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     */
    void destroy(Integer answerId, AccountUser accountUser);
}
