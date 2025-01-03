package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.security.AccountUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuestionPolicyTest {

    @InjectMocks
    private QuestionPolicy questionPolicy;

    @Mock
    private AnswerMapper answerMapper;
    @Mock
    private QuestionMapper questionMapper;

    @Test
    void can_judge_user_is_question_owner_when_mark_best_answer() {
        // given
        Question publishedQuestion = QuestionFactory.createPublishedQuestion();
        given(questionMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(publishedQuestion);
        Answer answer = AnswerFactory.createAnswer(publishedQuestion.getId());
        given(answerMapper.selectByPrimaryKey(answer.getId())).willReturn(answer);

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        boolean questionOwner = questionPolicy.canMarkAnswerAsBest(1, accountUser);

        // then
        assertThat(questionOwner).isTrue();
    }

    @Test
    void answer_and_question_are_both_valid() {
        // given
        AccountUser accountUser = UserFactory.createAccountUser();
        given(answerMapper.selectByPrimaryKey(anyInt())).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            questionPolicy.canMarkAnswerAsBest(1, accountUser);
        }).isInstanceOf(AnswerNotExistedException.class)
                .hasMessageContaining("answer not exist");


        // given
        Answer answer = AnswerFactory.createAnswer(1);
        given(answerMapper.selectByPrimaryKey(1)).willReturn(answer);
        given(questionMapper.selectByPrimaryKey(anyInt())).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            questionPolicy.canMarkAnswerAsBest(1, accountUser);
        }).isInstanceOf(QuestionNotExistedException.class)
                .hasMessageContaining("question not exist");

        // given
        Question unpublishedQuestion = QuestionFactory.createUnpublishedQuestion();
        unpublishedQuestion.setId(1);
        given(questionMapper.selectByPrimaryKey(unpublishedQuestion.getId())).willReturn(unpublishedQuestion);
        Answer anotherAnswer = AnswerFactory.createAnswer(unpublishedQuestion.getId());
        given(answerMapper.selectByPrimaryKey(anotherAnswer.getId())).willReturn(anotherAnswer);

        // then
        assertThatThrownBy(() -> {
            // when
            questionPolicy.canMarkAnswerAsBest(1, accountUser);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageContaining("question not publish");
    }
}