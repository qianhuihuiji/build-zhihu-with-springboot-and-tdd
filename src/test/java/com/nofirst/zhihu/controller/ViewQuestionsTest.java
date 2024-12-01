package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.config.SecurityConfig;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.service.QuestionService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ViewQuestionsTest
 * todo: controller 层应视为集成测试，启动spring容器、数据库等进行真实测试
 *
 * @author nofirst
 * @date 2020-08-24 21:52
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QuestionController.class)
@Import({SecurityConfig.class})
// SpringSecurity 提供的测试注解，注入一个Mock的User，模拟登录、授权的情况
@WithMockUser
class ViewQuestionsTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;
    // 这个是SpringSecurity需要的
    @MockBean
    private UserMapper userMapper;

    @Test
    void user_can_view_a_single_question() throws Exception {
        QuestionVo questionVo = new QuestionVo();
        questionVo.setId(1);
        questionVo.setUserId(1);
        questionVo.setTitle("title");
        questionVo.setContent("content");

        when(this.questionService.show(1)).thenReturn(questionVo);
        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"));
    }

    @Test
    void user_can_view_a_published_question() throws Exception {
        Question question = QuestionFactory.createPublishedQuestion();
        question.setId(1);
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);
        question.setPublishedAt(lastWeek);
        QuestionVo questionVo = QuestionFactory.createVO(question);
        when(this.questionService.show(1)).thenReturn(questionVo);

        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("this is a published question"))
                .andExpect(jsonPath("content").value("published content"));
    }

    @Test
    void user_can_not_view_unpublished_question() throws Exception {
        when(this.questionService.show(any())).thenThrow(new QuestionNotPublishedException());

        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("question not publish"));
    }

    @Test
    void can_see_answers_when_view_a_published_question() throws Exception {
        Question question = QuestionFactory.createPublishedQuestion();
        question.setId(1);
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);
        question.setPublishedAt(lastWeek);
        QuestionVo questionVo = QuestionFactory.createVO(question);

        Answer answer1 = AnswerFactory.createAnswer(question.getId());
        Answer answer2 = AnswerFactory.createAnswer(question.getId());
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        PageInfo<Answer> answerPageInfo = new PageInfo<>(answers);
        questionVo.setAnswers(answerPageInfo);
        when(this.questionService.show(1)).thenReturn(questionVo);

        MvcResult result = this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();
        QuestionVo content = JSONUtil.toBean(json, QuestionVo.class);
        assertThat(content).isNotNull();
        assertThat(content.getId()).isEqualTo(1L);
        assertThat(content.getTitle()).isEqualTo("this is a published question");
        assertThat(content.getContent()).isEqualTo("published content");
        assertThat(content.getAnswers().getList().size()).isEqualTo(2);
    }
}
