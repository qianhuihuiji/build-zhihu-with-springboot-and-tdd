package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface QuestionVoteDownService {

    void store(Long questionId, AccountUser accountUser);

    void destroy(Long questionId, AccountUser accountUser);
}
