package com.nofirst.zhihu.service;

import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.matcher.AnswerMatcher;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    @Mock
    private QuestionMapper questionMapper;

    private Answer answer;

    @BeforeEach
    public void setup() {
        this.answer = AnswerFactory.createAnswer(1L);
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
    void can_post_an_answer_to_a_published_question() {
        // given
        // when
        answerService.store(1L, this.answer);

        // then
        verify(answerMapper, times(1)).insert(argThat(new AnswerMatcher(answer)));
    }

    @Test
    void can_not_post_an_answer_to_an_unpublished_question() {
        // given
        Question unpublishedQuestion = QuestionFactory.createUnpublishedQuestion();
        given(questionMapper.selectByPrimaryKey(unpublishedQuestion.getId())).willReturn(unpublishedQuestion);

        // then
        assertThatThrownBy(() -> {
            // when
            answerService.store(unpublishedQuestion.getId(), this.answer);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageStartingWith("question not publish");
    }
}