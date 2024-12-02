package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionSubscribeService
 *
 * @author nofirst
 */
public interface QuestionSubscribeService {


    void subscribe(Integer questionId, AccountUser accountUser);

    void unsubscribe(Integer questionId, AccountUser accountUser);
}
