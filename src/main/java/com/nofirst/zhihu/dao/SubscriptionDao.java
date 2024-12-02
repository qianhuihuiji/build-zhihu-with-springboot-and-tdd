package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Subscription;

import java.util.List;

public interface SubscriptionDao {

    List<Subscription> selectByQuestionId(Integer questionId);

}