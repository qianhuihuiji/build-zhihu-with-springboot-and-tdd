package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.SubscriptionFactory;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.SubscriptionMapper;
import com.nofirst.zhihu.mbg.model.NotificationExample;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.model.dto.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NotificationsTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        notificationMapper.deleteByExample(null);
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void a_notification_is_prepared_when_a_subscribed_question_receives_a_new_answer_by_other_people() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        question.setUserId(1);
        questionMapper.insert(question);

        Subscription subscription = SubscriptionFactory.createSubscription(1, question.getId());
        subscriptionMapper.insert(subscription);

        NotificationExample notificationExample = new NotificationExample();
        NotificationExample.Criteria criteria = notificationExample.createCriteria();
        // 用户id为 1 的通知
        criteria.andNotifiableIdEqualTo(1);
        long beforeCount = notificationMapper.countByExample(notificationExample);
        assertThat(beforeCount).isEqualTo(0);
        // when
        AnswerDto answer = AnswerFactory.createAnswerDto();
        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(answer))
        );

        // then
        long afterCount = notificationMapper.countByExample(notificationExample);
        assertThat(afterCount).isEqualTo(1);
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void all_invited_users_are_notified_when_publish_question() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setUserId(2);
        question.setContent("@Jane @Foo please help me");
        questionMapper.insert(question);

        NotificationExample notificationExample = new NotificationExample();
        NotificationExample.Criteria criteria = notificationExample.createCriteria();
        // 查用户id为1的，也就是 Jane 
        criteria.andNotifiableIdEqualTo(1);
        long beforeCountOfJane = notificationMapper.countByExample(notificationExample);
        assertThat(beforeCountOfJane).isEqualTo(0);
        // 查用户id为3的，也就是 Foo
        notificationExample.clear();
        criteria.andNotifiableIdEqualTo(3);
        long beforeCountOfFoo = notificationMapper.countByExample(notificationExample);
        assertThat(beforeCountOfFoo).isEqualTo(0);
        // when
        this.mockMvc.perform(post("/questions/{questionId}/published-questions", question.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        // 查用户id为1的，也就是 Jane
        notificationExample.clear();
        criteria = notificationExample.createCriteria();
        criteria.andNotifiableIdEqualTo(1);
        long afterCountOfJane = notificationMapper.countByExample(notificationExample);
        assertThat(afterCountOfJane).isEqualTo(1);
        // 查用户id为3的，也就是 Foo
        notificationExample.clear();
        criteria = notificationExample.createCriteria();
        criteria.andNotifiableIdEqualTo(3);
        long afterCountOfFoo = notificationMapper.countByExample(notificationExample);
        assertThat(afterCountOfFoo).isEqualTo(1);
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void mentioned_users_are_notified_when_comment_a_question() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(question);

        NotificationExample notificationExample = new NotificationExample();
        NotificationExample.Criteria criteria = notificationExample.createCriteria();
        // 查用户id为1的，也就是 Jane
        criteria.andNotifiableIdEqualTo(1);
        long beforeCountOfJane = notificationMapper.countByExample(notificationExample);
        assertThat(beforeCountOfJane).isEqualTo(0);
        // 查用户id为3的，也就是 Foo
        notificationExample.clear();
        criteria.andNotifiableIdEqualTo(3);
        long beforeCountOfFoo = notificationMapper.countByExample(notificationExample);
        assertThat(beforeCountOfFoo).isEqualTo(0);
        // when
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("this is a comment");
        this.mockMvc.perform(post("/questions/{questionId}/comments", question.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(commentDto))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        // 查用户id为1的，也就是 Jane
        notificationExample.clear();
        criteria = notificationExample.createCriteria();
        criteria.andNotifiableIdEqualTo(1);
        long afterCountOfJane = notificationMapper.countByExample(notificationExample);
        assertThat(afterCountOfJane).isEqualTo(1);
        // 查用户id为3的，也就是 Foo
        notificationExample.clear();
        criteria = notificationExample.createCriteria();
        criteria.andNotifiableIdEqualTo(3);
        long afterCountOfFoo = notificationMapper.countByExample(notificationExample);
        assertThat(afterCountOfFoo).isEqualTo(1);
    }

}
