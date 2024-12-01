package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.service.AnswerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

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
class AnswersUpVotesTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private AnswerService answerService;

    @BeforeAll
    public static void start() {
        mySQLContainer.start();
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

//        mySQLContainer.start();
    }

    // 这里的 mysql:8.0 镜像最好先本地下载，不然启动测试会先尝试下载，测试时间会变得非常长
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("zhihu")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }


    @Test
    void guest_can_not_vote_up() throws Exception {
        this.mockMvc.perform(post("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void authenticated_user_can_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        Vote vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNotNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void an_authenticated_user_can_cancel_vote_up() throws Exception {
        // given
        this.mockMvc.perform(post("/answers/1/up-votes"));
        Vote vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNotNull();
        // when
        this.mockMvc.perform(delete("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        vote = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(vote).isNull();
    }

    @Test
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void can_vote_up_only_once() {
        // given
        try {
            this.mockMvc.perform(post("/answers/1/up-votes"));
            this.mockMvc.perform(post("/answers/1/up-votes"));
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
        this.mockMvc.perform(post("/answers/1/up-votes"));

        // when
        Boolean votedUp = answerService.isVotedUp(1L);

        // then
        Assertions.assertThat(votedUp).isTrue();
    }
}
