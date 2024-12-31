package com.nofirst.zhihu.service;

import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionSubscribeService
 *
 * @author nofirst
 */
public interface QuestionSubscribeService {


    /**
     * Subscribe.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     */
    void subscribe(Integer questionId, AccountUser accountUser);

    /**
     * Unsubscribe.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     */
    void unsubscribe(Integer questionId, AccountUser accountUser);

    /**
     * Is subscribed to boolean.
     *
     * @param questionId the question id
     * @param userId     the user id
     * @return the boolean
     */
    Boolean isSubscribedTo(Integer questionId, Integer userId);

    /**
     * Subscriptions count long.
     *
     * @param questionId the question id
     * @param userId     the user id
     * @return the long
     */
    long subscriptionsCount(Integer questionId, Integer userId);
}
