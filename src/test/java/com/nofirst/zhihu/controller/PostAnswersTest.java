package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
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
class PostAnswersTest {

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AnswerMapper answerMapper;

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
    // 这个注解会尝试在SpringSecurity上下文中注入一个username为 John 的用户
    // 而这个用户是初始化脚本插入的，所以 accountUserDetailsService 会根据名字找到id为2的User出来
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void signed_in_user_can_post_an_answer_to_a_published_question() throws Exception {
        // given
        // 创建一个 question
        Question question = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(question);

        AnswerDto answer = AnswerFactory.createAnswerDto();
        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(answer))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));

        //注1：这样验证时因为不是同一个对象，测试会报参数不匹配从而测试失败
//        verify(answerService, times(1)).store(1L, answer);
        // 注2：重载 Answer 类的 equals 方法可以让测试通过
        // 注3：还可以通过自定义 matcher 的方式来进行测试，特别注意，有多个参数时，每个参数都需要mock，如eq(1L)
//        verify(answerService, times(1)).store(eq(1L), argThat(new AnswerMatcher(answer)));

        List<Answer> answers = answerMapper.selectByUserId(2L);
        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    void guests_may_not_post_an_answer() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        question.setId(1L);

        AnswerDto answer = AnswerFactory.createAnswerDto();
        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(answer))
                )
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    // 只需要一个用户即可，至于真不真实不重要，因为我的测试的重点是前置校验
    @WithMockUser
    void content_is_required_to_post_answers() throws Exception {
        // given
        AnswerDto answerDto = AnswerFactory.createAnswerDto();
        answerDto.setContent("");

        // when
        this.mockMvc.perform(post("/questions/{id}/answers", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDto))
                )
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("答案内容不能为空"));
    }
}
