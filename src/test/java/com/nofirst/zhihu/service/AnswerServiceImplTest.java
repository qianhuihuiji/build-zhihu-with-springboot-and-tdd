package com.nofirst.zhihu.service;

import com.nofirst.zhihu.matcher.AnswerMatcher;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @InjectMocks
    private AnswerServiceImpl answerService;

    @Mock
    private AnswerMapper answerMapper;

    private Answer answer;

    @BeforeEach
    public void setup() {
        Date now = new Date();
        Answer answer1 = new Answer();
        answer1.setId(1L);
        answer1.setQuestionId(1L);
        answer1.setUserId(1);
        answer1.setCreatedAt(now);
        answer1.setUpdatedAt(now);
        answer1.setContent("this is a answer");

        this.answer = answer1;

    }

    @Test
    void get_existed_answer_by_show_method() {
        // given
        given(answerMapper.selectByPrimaryKey(1L)).willReturn(this.answer);

        // when
        Answer result = answerService.show(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(this.answer.getId());
        assertThat(result.getUserId()).isEqualTo(this.answer.getUserId());
        assertThat(result.getQuestionId()).isEqualTo(this.answer.getQuestionId());
        assertThat(result.getContent()).isEqualTo(this.answer.getContent());
    }

    @Test
    void can_store_an_answer() {
        // given

        // when
        answerService.store(1L, this.answer);

        // then
        verify(answerMapper, times(1)).insert(argThat(new AnswerMatcher(answer)));

    }
}