package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.service.AnswerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 会启动完整的Spring容器，因此会非常耗时
@SpringBootTest(classes = BuildZhihuWithSpringbootAndTddApplication.class)
class DownVotesTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private AnswerService answerService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    void guest_can_not_vote_down() throws Exception {
        this.mockMvc.perform(post("/answers/1/down-votes"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void authenticated_user_can_vote_down() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/down-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        Vote vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode());
        assertThat(vote).isNotNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void an_authenticated_user_can_cancel_vote_down() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/down-votes"));
        Vote vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode());
        assertThat(vote).isNotNull();
        // when
        this.mockMvc.perform(delete("/answers/1/down-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode());
        assertThat(vote).isNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void can_vote_down_only_once() {
        // given
        try {
            this.mockMvc.perform(post("/answers/1/down-votes"));
            this.mockMvc.perform(post("/answers/1/down-votes"));
        } catch (Exception e) {
            fail("Can not vote up twice", e);
        }
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void answer_can_know_it_is_voted_down() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/down-votes"));

        // when
        Boolean votedUp = answerService.isVotedDown(1L);

        // then
        Assertions.assertThat(votedUp).isTrue();
    }
}