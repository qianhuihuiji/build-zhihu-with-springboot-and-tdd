package com.nofirst.zhihu.service;

import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.SubscriptionFactory;
import com.nofirst.zhihu.mbg.mapper.SubscriptionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.service.impl.QuestionSubscribeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionSubscribeServiceImplTest {

    @InjectMocks
    private QuestionSubscribeServiceImpl questionSubscribeService;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @Test
    void can_know_it_if_subscribed_to() {
        Question pushlishedQuestion = QuestionFactory.createPublishedQuestion();
        pushlishedQuestion.setId(1);
        pushlishedQuestion.setUserId(1);
        Subscription subscription = SubscriptionFactory.createSubscription(1, 1);
        when(this.subscriptionMapper.countByExample(any())).thenReturn(1L);

        // when
        Boolean isSubscribed = questionSubscribeService.isSubscribedTo(1, 1);

        // then
        assertThat(isSubscribed).isTrue();
    }

    @Test
    void can_know_subscriptions_count() {
        when(this.subscriptionMapper.countByExample(any())).thenReturn(1L);

        // when
        long subscriptionsCount = questionSubscribeService.subscriptionsCount(1, 1);

        // then
        assertThat(subscriptionsCount).isEqualTo(1);
    }
}