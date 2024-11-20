package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteType;
import com.nofirst.zhihu.model.enums.VoteUpType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 会启动完整的Spring容器，因此会非常耗时
@SpringBootTest(classes = BuildZhihuWithSpringbootAndTddApplication.class)
class UpVotesTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private VoteMapper voteMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    void guest_can_not_vote_up() throws Exception {
        this.mockMvc.perform(post("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void authenticated_user_can_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        List<Vote> votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(1);
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 another_user 的用户
    // 而这个用户是初始化脚本 data.sql 插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "another_user", userDetailsServiceBeanName = "accountUserDetailsService")
    void an_authenticated_user_can_cancel_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/up-votes"));
        List<Vote> votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(1);
        // when
        this.mockMvc.perform(delete("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(0);
    }
}
