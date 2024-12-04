package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.AnswerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    private QuestionMapper questionMapper;

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
    void guests_may_not_create_questions() throws Exception {
        Question question = QuestionFactory.createUnpublishedQuestion();
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))

                .andDo(print())
                .andExpect(status().is(401));
    }


    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void an_authenticated_user_can_create_new_questions() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        long beforeCount = questionMapper.countByExample(null);

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        long afterCount = questionMapper.countByExample(null);
        // 调用之后 question 增加了 1 条
        assertThat(afterCount - beforeCount).isEqualTo(1);
    }

    @Test
    @WithMockUser
    void title_is_required() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setTitle("");

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("标题不能为空"));
    }

    @Test
    @WithMockUser
    void content_is_required() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setContent("");

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("内容不能为空"));
    }

    @Test
    @WithMockUser
    void category_id_is_required() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setCategoryId(null);

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("问题分类不能为空"));
    }

    @Test
    @WithMockUser
    void category_id_is_valid() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setCategoryId((short) 999);

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                // then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("问题分类不存在"));
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void get_slug_when_create_a_question() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setTitle("英语 英语");
        long beforeCount = questionMapper.countByExample(null);

        // when
        this.mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(question)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        // then
        long afterCount = questionMapper.countByExample(null);
        // 调用之后 question 增加了 1 条
        assertThat(afterCount - beforeCount).isEqualTo(1);
        List<Question> questions = questionMapper.selectByExample(null);
        Question result = questions.get(0);
        assertThat(result.getSlug()).isEqualTo("english-english");
    }
}
