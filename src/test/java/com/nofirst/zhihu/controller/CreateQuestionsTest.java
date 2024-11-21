package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 会启动完整的Spring容器，因此会非常耗时
@SpringBootTest(classes = BuildZhihuWithSpringbootAndTddApplication.class)
class CreateQuestionsTest {

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
    void guest_can_not_vote_up() throws Exception {
        this.mockMvc.perform(post("/answers/1/up-votes"))
                .andDo(print())
                .andExpect(status().is(401));
    }
}
