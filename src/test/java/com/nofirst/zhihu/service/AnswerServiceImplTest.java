package com.nofirst.zhihu.service;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.matcher.AnswerMatcher;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
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

    private Answer defaultAnswer;
    private AnswerDto defaultAnswerDto;

    @BeforeEach
    public void setup() {
        this.defaultAnswer = AnswerFactory.createAnswer(1L);
        this.defaultAnswerDto = AnswerFactory.createAnswerDto();
    }

    @Test
    void get_existed_answer_by_show_method() {
        // given
        given(answerMapper.selectByPrimaryKey(1L)).willReturn(this.defaultAnswer);

        // when
        Answer result = answerService.show(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(this.defaultAnswer.getId());
        assertThat(result.getUserId()).isEqualTo(this.defaultAnswer.getUserId());
        assertThat(result.getQuestionId()).isEqualTo(this.defaultAnswer.getQuestionId());
        assertThat(result.getContent()).isEqualTo(this.defaultAnswer.getContent());
    }

    @Test
    void can_post_an_answer_to_a_published_question() {
        // given
        Question publishedQuestion = QuestionFactory.createPublishedQuestion();
        given(questionMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(publishedQuestion);

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerService.store(1L, this.defaultAnswerDto, accountUser);

        // then
        verify(answerMapper, times(1)).insert(argThat(new AnswerMatcher(defaultAnswer)));
    }

    @Test
    void can_not_post_an_answer_to_an_unpublished_question() {
        // given
        Question unpublishedQuestion = QuestionFactory.createUnpublishedQuestion();
        given(questionMapper.selectByPrimaryKey(unpublishedQuestion.getId())).willReturn(unpublishedQuestion);

        // then
        assertThatThrownBy(() -> {
            // when
            AccountUser accountUser = UserFactory.createAccountUser();
            answerService.store(unpublishedQuestion.getId(), this.defaultAnswerDto, accountUser);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageStartingWith("question not publish");
    }

    @Test
    void can_mark_one_answer_as_the_best() {
        // given
        Question publishedQuestion = QuestionFactory.createPublishedQuestion();
        given(questionMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(publishedQuestion);
        Answer answer = AnswerFactory.createAnswer(publishedQuestion.getId());
        given(answerMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(answer);

        // when
        answerService.markAsBest(1L);

        // then
        verify(questionMapper, times(1)).markAsBestAnswer(publishedQuestion.getId(), answer.getId());
        assertThat(answer.isBest(publishedQuestion)).isTrue();
    }

    @Test
    void answer_and_question_are_both_valid_when_mark_one_answer_as_the_best() {
        // given
        given(answerMapper.selectByPrimaryKey(anyLong())).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            answerService.markAsBest(1L);
        }).isInstanceOf(AnswerNotExistedException.class)
                .hasMessageContaining("answer not exist");


        // given
        Answer answer = AnswerFactory.createAnswer(1L);
        given(answerMapper.selectByPrimaryKey(1L)).willReturn(answer);
        given(questionMapper.selectByPrimaryKey(anyLong())).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            answerService.markAsBest(1L);
        }).isInstanceOf(QuestionNotExistedException.class)
                .hasMessageContaining("question not exist");

        // given
        Question unpublishedQuestion = QuestionFactory.createUnpublishedQuestion();
        given(questionMapper.selectByPrimaryKey(unpublishedQuestion.getId())).willReturn(unpublishedQuestion);
        Answer anotherAnswer = AnswerFactory.createAnswer(unpublishedQuestion.getId());
        given(answerMapper.selectByPrimaryKey(unpublishedQuestion.getId())).willReturn(anotherAnswer);

        // then
        assertThatThrownBy(() -> {
            // when
            answerService.markAsBest(1L);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageContaining("question not publish");
    }
}