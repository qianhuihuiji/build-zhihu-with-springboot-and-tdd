package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface AnswerVoteUpService {

    void store(Integer answerId, AccountUser accountUser);

    void destroy(Integer answerId, AccountUser accountUser);
}
