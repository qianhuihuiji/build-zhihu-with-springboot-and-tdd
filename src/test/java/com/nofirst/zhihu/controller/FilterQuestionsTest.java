package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.BuildZhihuWithSpringbootAndTddApplication;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.QuestionVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 会启动完整的Spring容器，因此会非常耗时
@SpringBootTest(classes = BuildZhihuWithSpringbootAndTddApplication.class)
class FilterQuestionsTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CategoryMapper categoryMapper;

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

        questionMapper.deleteByExample(null);
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
    void user_can_see_published_questions_without_any_filter() throws Exception {
        // given
        // 10 条已发布
        List<Question> publishedQuestions = QuestionFactory.createPublishedQuestionBatch(10);
        publishedQuestions.forEach(t -> questionMapper.insert(t));
        // 中间插入一条未发布的
        Question unpublishedQuestion = QuestionFactory.createUnpublishedQuestion();
        questionMapper.insert(unpublishedQuestion);
        // 再来 30 条已发布
        List<Question> publishedQuestions2 = QuestionFactory.createPublishedQuestionBatch(30);
        publishedQuestions2.forEach(t -> questionMapper.insert(t));

        // when
        MvcResult result = this.mockMvc.perform(get("/questions?pageIndex=1&pageSize=20"))
                .andExpect(status().isOk()).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult commonResult = JSONUtil.toBean(json, CommonResult.class);
        long code = commonResult.getCode();
        assertThat(code).isEqualTo(ResultCode.SUCCESS.getCode());

        PageInfo<QuestionVo> data = JSONUtil.toBean((JSONObject) commonResult.getData(), PageInfo.class);

        assertThat(json).doesNotContain(unpublishedQuestion.getContent());
        assertThat(data.getTotal()).isEqualTo(40);
        assertThat(data.getList().size()).isEqualTo(20);
    }

    @Test
    void user_can_filter_questions_by_category() throws Exception {
        // given
        Category category = categoryMapper.selectByPrimaryKey((short) 1);
        Question inSlug = QuestionFactory.createPublishedQuestion();
        inSlug.setCategoryId(category.getId());
        inSlug.setTitle("i am in slug");
        questionMapper.insert(inSlug);
        Question notInSlug = QuestionFactory.createPublishedQuestion();
        notInSlug.setCategoryId((short) 999);
        notInSlug.setTitle("i am not in slug");
        questionMapper.insert(notInSlug);

        // when
        MvcResult result = this.mockMvc.perform(get("/questions?slug={slug}&pageIndex=1&pageSize=20", category.getSlug()))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(inSlug.getTitle());
        assertThat(json).doesNotContain(notInSlug.getTitle());
    }

    @Test
    void user_can_filter_questions_by_username() throws Exception {
        // given
        Question byJohn = QuestionFactory.createPublishedQuestion();
        byJohn.setTitle("question by john");
        byJohn.setUserId(2);
        questionMapper.insert(byJohn);
        Question byOther = QuestionFactory.createPublishedQuestion();
        byOther.setTitle("question by other");
        byOther.setUserId(999);
        questionMapper.insert(byOther);
        // when
        MvcResult result = this.mockMvc.perform(get("/questions?pageIndex=1&pageSize=20&by=John"))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(byJohn.getTitle());
        assertThat(json).doesNotContain(byOther.getTitle());
    }

    @Test
    void user_can_filter_questions_by_popularity() throws Exception {
        // given
        List<Answer> answerBatch = new ArrayList<>();
        Question oneAnswerQuestion = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(oneAnswerQuestion);
        answerBatch.addAll(AnswerFactory.createAnswerBatch(1, oneAnswerQuestion.getId()));
        Question twoAnswerQuestion = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(twoAnswerQuestion);
        answerBatch.addAll(AnswerFactory.createAnswerBatch(2, twoAnswerQuestion.getId()));
        Question threeAnswerQuestion = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(threeAnswerQuestion);
        answerBatch.addAll(AnswerFactory.createAnswerBatch(3, threeAnswerQuestion.getId()));
        // when
        MvcResult result = this.mockMvc.perform(get("/questions?pageIndex=1&pageSize=20&popularity=1"))
                .andExpect(status().isOk()).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult commonResult = JSONUtil.toBean(json, CommonResult.class);
        long code = commonResult.getCode();
        assertThat(code).isEqualTo(ResultCode.SUCCESS.getCode());

        PageInfo<QuestionVo> data = JSONUtil.toBean((JSONObject) commonResult.getData(), PageInfo.class);
        List<QuestionVo> questionVos = data.getList();
        List<Integer> answersCountList = questionVos.stream().map(QuestionVo::getAnswersCount).collect(Collectors.toList());

        assertThat(Arrays.asList(3, 2, 1)).isEqualTo(answersCountList);
    }

}
