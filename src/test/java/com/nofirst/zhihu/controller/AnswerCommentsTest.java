package com.nofirst.zhihu.controller;

import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.CommentMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.CommentExample;
import com.nofirst.zhihu.model.dto.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnswerCommentsTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private CommentMapper commentMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        questionMapper.deleteByExample(null);
        answerMapper.deleteByExample(null);
        commentMapper.deleteByExample(null);
    }


    @Test
    void guests_may_not_comment_an_answer() throws Exception {
        this.mockMvc.perform(post("/answers/1/comments"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void signed_in_user_can_comment_an_answer() throws Exception {
        // given
        Answer answer = AnswerFactory.createAnswer(1);
        answerMapper.insert(answer);

        long beforeCount = commentMapper.countByExample(null);
        assertThat(beforeCount).isEqualTo(0);

        // when
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("this is a comment");
        this.mockMvc.perform(post("/answers/{answerId}/comments", answer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(commentDto))
                ).andDo(print())
                .andExpect(status().isOk());

        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andCommentedIdEqualTo(answer.getId());
        criteria.andCommentedTypeEqualTo(Answer.class.getSimpleName());
        long agerCount = commentMapper.countByExample(example);
        assertThat(agerCount).isEqualTo(1);
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void content_is_required_to_comment_an_answer() throws Exception {
        // given
        // given
        Answer answer = AnswerFactory.createAnswer(1);
        answerMapper.insert(answer);

        long beforeCount = commentMapper.countByExample(null);
        assertThat(beforeCount).isEqualTo(0);

        // when
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(null);
        this.mockMvc.perform(post("/answers/{answerId}/comments", answer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(commentDto))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.VALIDATE_FAILED.getCode()))
                .andExpect(jsonPath("$.message").value("评论内容不能为空"));
    }

}
