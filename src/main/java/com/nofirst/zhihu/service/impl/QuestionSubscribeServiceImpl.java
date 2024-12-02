package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.SubscriptionMapper;
import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.mbg.model.SubscriptionExample;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * The type Question subscribe service.
 */
@Service
@AllArgsConstructor
public class QuestionSubscribeServiceImpl implements QuestionSubscribeService {

    private final SubscriptionMapper subscriptionMapper;

    @Override
    public void subscribe(Integer questionId, AccountUser accountUser) {
        Subscription subscription = new Subscription();
        subscription.setUserId(accountUser.getUserId());
        subscription.setQuestionId(questionId);
        Date now = new Date();
        subscription.setCreateTime(now);
        subscription.setUpdateTime(now);

        subscriptionMapper.insert(subscription);
    }

    @Override
    public void unsubscribe(Integer questionId, AccountUser accountUser) {
        SubscriptionExample example = new SubscriptionExample();
        SubscriptionExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(accountUser.getUserId());
        criteria.andQuestionIdEqualTo(questionId);

        subscriptionMapper.deleteByExample(example);
    }

    @Override
    public Boolean isSubscribedTo(Integer questionId, Integer userId) {
        SubscriptionExample example = new SubscriptionExample();
        example.createCriteria().andQuestionIdEqualTo(questionId).andUserIdEqualTo(userId);
        long count = subscriptionMapper.countByExample(example);
        return count > 0;
    }

    @Override
    public long subscriptionsCount(Integer questionId, Integer userId) {
        SubscriptionExample example = new SubscriptionExample();
        example.createCriteria().andQuestionIdEqualTo(questionId).andUserIdEqualTo(userId);
        return subscriptionMapper.countByExample(example);
    }
}
