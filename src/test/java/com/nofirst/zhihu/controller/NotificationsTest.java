package com.nofirst.zhihu.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.CommonResult;
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
import com.nofirst.zhihu.model.vo.NotificationVo;
import com.nofirst.zhihu.security.AccountUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

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
        commentDto.setContent("@Jane @Foo look at this");
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

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void a_user_can_fetch_their_unread_notifications() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        question.setUserId(1);
        questionMapper.insert(question);
        // 1号用户订阅了一个问题，如果有人发表了答案，他会收到一条通知
        Subscription subscription = SubscriptionFactory.createSubscription(1, question.getId());
        subscriptionMapper.insert(subscription);
        AnswerDto answer = AnswerFactory.createAnswerDto();
        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(answer))
        );


        // when
        MvcResult result = this.mockMvc.perform(get("/notifications?pageIndex=1&pageSize=10")
                .with(user(accountUserDetailsService.loadUserByUsername("Jane")))
        ).andExpect(status().isOk()).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult<PageInfo<NotificationVo>> commonResult = JSONUtil.toBean(json, new TypeReference<>() {
        }, false);
        long code = commonResult.getCode();
        assertThat(code).isEqualTo(ResultCode.SUCCESS.getCode());

        PageInfo<NotificationVo> data = commonResult.getData();
        assertThat(data.getTotal()).isEqualTo(1);
        assertThat(data.getList().size()).isEqualTo(1);

    }

    @Test
    void guests_can_not_see_unread_notifications() throws Exception {
        this.mockMvc.perform(get("/notifications"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void clear_all_unread_notifications_after_see_unread_notifications_page() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        question.setUserId(1);
        questionMapper.insert(question);
        // 1号用户订阅了一个问题，如果有人发表了答案，他会收到一条通知
        Subscription subscription = SubscriptionFactory.createSubscription(1, question.getId());
        subscriptionMapper.insert(subscription);

        // 初始的未读通知数量是0
        NotificationExample example = new NotificationExample();
        example.createCriteria().andNotifiableIdEqualTo(1).andReadAtIsNull();
        long beforeCount = notificationMapper.countByExample(example);
        assertThat(beforeCount).isEqualTo(0);

        AnswerDto answer = AnswerFactory.createAnswerDto();
        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(answer))
        );
        // 未读通知数量变成1
        long afterCount = notificationMapper.countByExample(example);
        assertThat(afterCount).isEqualTo(1);

        // when
        // 切换到1号用户进行访问
        this.mockMvc.perform(get("/notifications?pageIndex=1&pageSize=10")
                .with(user(accountUserDetailsService.loadUserByUsername("Jane")))
        ).andExpect(status().isOk()).andReturn();

        // then
        // 最终未读通知数量变成0
        long finalCount = notificationMapper.countByExample(example);
        assertThat(finalCount).isEqualTo(0);

    }
}
