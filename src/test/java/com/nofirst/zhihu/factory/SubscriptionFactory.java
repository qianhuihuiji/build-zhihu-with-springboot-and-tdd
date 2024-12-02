package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Subscription;

import java.util.Date;

public class SubscriptionFactory {

    public static Subscription createSubscription(Integer userId, Integer questionId) {
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setQuestionId(questionId);
        Date now = new Date();
        subscription.setCreateTime(now);
        subscription.setUpdateTime(now);

        return subscription;
    }

}
