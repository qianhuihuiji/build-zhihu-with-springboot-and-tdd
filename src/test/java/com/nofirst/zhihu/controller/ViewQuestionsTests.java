package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ViewQuestionsTests
 * todo: controller 层应视为集成测试，启动spring容器、数据库等进行真实测试
 *
 * @author nofirst
 * @date 2020-08-24 21:52
 */
@RunWith(SpringRunner.class)
@WebMvcTest(QuestionsController.class)
class ViewQuestionsTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper questionMapper;

    @Test
    void user_can_view_a_single_question() throws Exception {
        when(this.questionService.show(1L)).thenReturn(new Question(1L, 1, "title", "content"));
        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"));
    }

    @Test
    void user_can_view_a_published_question() throws Exception {
        Question question = new Question(1L, 1, "title", "content");
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);
        question.setPublishedAt(lastWeek);
        when(this.questionService.show(1L)).thenReturn(question);

        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"));
    }

    @Test
    void user_can_not_view_unpublished_question() throws Exception {
        when(this.questionService.show(any())).thenThrow(new QuestionNotPublishedException());

        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("question not publish"));
    }
}
