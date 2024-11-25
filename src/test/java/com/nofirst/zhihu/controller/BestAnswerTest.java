package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.config.SecurityConfig;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.policy.QuestionPolicy;
import com.nofirst.zhihu.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 最佳答案测试
 *
 * @author nofirst
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BestAnswerController.class)
@Import({SecurityConfig.class})
class BestAnswerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private QuestionMapper questionMapper;
    @MockBean
    private AnswerMapper answerMapper;
    // 这个bean是SpringSecurity需要的
    @MockBean
    private UserMapper userMapper;

    // 如果不加 name 属性，会报错，可以去掉name属性试一下 only_the_question_creator_can_mark_a_best_answer 测试用例
    @MockBean(name = "questionPolicy")
    private QuestionPolicy questionPolicy;

    @Test
    void guests_can_not_mark_best_answer() throws Exception {
        this.mockMvc.perform(post("/answers/{id}/best", 1))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser
        // 这里我们只验证 markAsBest() 方法被调用了一次即可，Service 的逻辑放到service里面进行测试
    void can_mark_one_answer_as_the_best() throws Exception {
        when(this.questionPolicy.canMarkAnswerAsBest(anyLong(), any())).thenReturn(true);
        this.mockMvc.perform(post("/answers/{answerId}/best", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()));
        verify(answerService, times(1)).markAsBest(anyLong(), any());
    }

    @Test
    @WithMockUser
    void only_the_question_creator_can_mark_a_best_answer() throws Exception {
        // given
        User user = UserFactory.createUser();
        when(this.userMapper.selectByUsername(anyString())).thenReturn(user);
        when(this.questionPolicy.canMarkAnswerAsBest(anyLong(), any())).thenReturn(false);
        this.mockMvc.perform(post("/answers/{id}/best", 1))
                .andExpect(status().is(403));
    }

}
