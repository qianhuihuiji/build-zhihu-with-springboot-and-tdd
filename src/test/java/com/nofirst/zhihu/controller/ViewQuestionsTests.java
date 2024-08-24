package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ViewQuestionsTests
 *
 * @author nofirst
 * @date 2020-08-24 21:52
 */
@RunWith(SpringRunner.class)
@WebMvcTest
class ViewQuestionsTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;

    @Test
    void user_can_view_a_single_question() throws Exception {
        when(this.questionService.show(1L)).thenReturn(new Question(1L, 1L, "title", "content"));
        this.mockMvc.perform(get("/questions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"));
    }
}
