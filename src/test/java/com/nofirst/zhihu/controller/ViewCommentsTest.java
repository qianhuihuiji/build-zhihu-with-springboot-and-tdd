package com.nofirst.zhihu.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.CommentFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.mapper.CommentMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.CommentVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ViewCommentsTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        questionMapper.deleteByExample(null);
    }

    @Test
    void can_request_all_comments_for_a_given_question() throws Exception {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(question);
        List<Comment> comments = CommentFactory.createBatch(40, question.getId(), question.getClass().getSimpleName());
        for (Comment comment : comments) {
            commentMapper.insert(comment);
        }

        // when
        MvcResult result = this.mockMvc.perform(get("/questions/{questionId}/comments", question.getId()))
                .andExpect(status().isOk()).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult<PageInfo<CommentVo>> commonResult = JSONUtil.toBean(json, new TypeReference<>() {
        }, false);
        long code = commonResult.getCode();
        assertThat(code).isEqualTo(ResultCode.SUCCESS.getCode());

        PageInfo<CommentVo> data = commonResult.getData();

        assertThat(data.getTotal()).isEqualTo(40);
        assertThat(data.getList().size()).isEqualTo(20);
    }

    @Test
    void can_request_all_comments_for_a_given_answer() throws Exception {
        // given
        Answer answer = AnswerFactory.createAnswer(1);
        answerMapper.insert(answer);
        List<Comment> comments = CommentFactory.createBatch(40, answer.getId(), answer.getClass().getSimpleName());
        for (Comment comment : comments) {
            commentMapper.insert(comment);
        }

        // when
        MvcResult result = this.mockMvc.perform(get("/answers/{answerId}/comments", answer.getId()))
                .andExpect(status().isOk()).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult<PageInfo<CommentVo>> commonResult = JSONUtil.toBean(json, new TypeReference<>() {
        }, false);
        long code = commonResult.getCode();
        assertThat(code).isEqualTo(ResultCode.SUCCESS.getCode());

        PageInfo<CommentVo> data = commonResult.getData();

        assertThat(data.getTotal()).isEqualTo(40);
        assertThat(data.getList().size()).isEqualTo(20);
    }


}
