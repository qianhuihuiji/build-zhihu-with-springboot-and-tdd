package com.nofirst.zhihu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AnswersController.class)
class PostAnswersTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnswerService answerService;

    @Test
    void user_can_post_an_answer_to_a_question() throws Exception {
        User user = new User(1, "luke");
        Question question = new Question(1L, user.getId(), "title", "content");
        Answer answer = new Answer(1L, user.getId());

        this.mockMvc.perform(post("/questions/{id}/answers", question.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer))
                )
                .andExpect(status().isOk());

        //注1：这样验证时因为不是同一个对象，测试会报参数不匹配从而测试失败
//        verify(answerService, times(1)).store(1L, answer);
        // 注2：重载 Answer 类的 equals 方法可以让测试通过
        // 注3：还可以通过自定义 matcher 的方式来进行测试，特别注意，有多个参数时，每个参数都需要mock，如eq(1L)
//        verify(answerService, times(1)).store(eq(1L), argThat(new AnswerMatcher(answer)));
    }

    @Test
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
