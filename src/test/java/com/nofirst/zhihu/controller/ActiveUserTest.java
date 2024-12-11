package com.nofirst.zhihu.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.UserVo;
import com.nofirst.zhihu.redis.JsonRedisTemplate;
import com.nofirst.zhihu.task.ActiveUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.nofirst.zhihu.task.ActiveUserService.CACHE_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActiveUserTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ActiveUserService activeUserService;

    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void can_calculate_active_user() {
        // given
        // John 创建了 1 个 Question，得 4 分
        Question question = QuestionFactory.createPublishedQuestion();
        question.setUserId(2);
        questionMapper.insert(question);
        // Jane 创建了 1 个 Answer，得 1 分
        Answer answer = AnswerFactory.createAnswer(question.getId());
        answer.setUserId(1);
        answerMapper.insert(answer);
        // 还有一个用户 Foo，不得分

        // when
        activeUserService.calculateAndCacheActiveUsers();

        // then
        List<UserVo> userVos = (List<UserVo>) jsonRedisTemplate.opsForValue().get(CACHE_KEY);
        assertThat(userVos).isNotNull();
        assertThat(userVos.size()).isEqualTo(2);
        assertThat(userVos.stream().map(UserVo::getId).collect(Collectors.toList())).isEqualTo(Arrays.asList(2, 1));
    }

    @Test
    void can_get_all_active_user() throws Exception {
        // given
        // John 创建了 1 个 Question，得 4 分
        Question question = QuestionFactory.createPublishedQuestion();
        question.setUserId(2);
        questionMapper.insert(question);
        // Jane 创建了 1 个 Answer，得 1 分
        Answer answer = AnswerFactory.createAnswer(question.getId());
        answer.setUserId(1);
        answerMapper.insert(answer);
        // 还有一个用户 Foo，不得分

        // when
        MvcResult result = this.mockMvc.perform(get("/active-users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode())).andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult<List<UserVo>> commonResult = JSONUtil.toBean(json, new TypeReference<>() {
        }, false);
        assertThat(commonResult.getData().size()).isEqualTo(2);
        assertThat(commonResult.getData().stream().map(UserVo::getId).collect(Collectors.toList())).isEqualTo(Arrays.asList(2, 1));
    }
}
