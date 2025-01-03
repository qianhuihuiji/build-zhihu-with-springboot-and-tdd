package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

class QuestionsUpVotesTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    void guest_can_not_vote_up() throws Exception {
        this.mockMvc.perform(post("/questions/1/up-votes"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void authenticated_user_can_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/questions/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        Vote vote = voteDao.selectByVotedId(1, Question.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNotNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void an_authenticated_user_can_cancel_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/questions/1/up-votes"));
        Vote vote = voteDao.selectByVotedId(1, Question.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNotNull();
        // when
        this.mockMvc.perform(delete("/questions/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        vote = voteDao.selectByVotedId(1, Question.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void can_vote_up_only_once() {
        // given
        try {
            this.mockMvc.perform(post("/questions/1/up-votes"));
            this.mockMvc.perform(post("/questions/1/up-votes"));
        } catch (Exception e) {
            fail("Can not vote up twice", e);
        }
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void answer_can_know_it_is_voted_up() throws Exception {
        // given
        this.mockMvc.perform(post("/questions/1/up-votes"));

        // when
        Boolean votedUp = questionService.isVotedUp(1);

        // then
        Assertions.assertThat(votedUp).isTrue();
    }
}
