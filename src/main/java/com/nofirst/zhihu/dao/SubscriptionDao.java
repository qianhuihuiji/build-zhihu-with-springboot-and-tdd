package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Subscription;

import java.util.List;

/**
 * The interface Subscription dao.
 */
public interface SubscriptionDao {

    /**
     * Select by question id list.
     *
     * @param questionId the question id
     * @return the list
     */
    List<Subscription> selectByQuestionId(Integer questionId);

}