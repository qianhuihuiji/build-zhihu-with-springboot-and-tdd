package com.nofirst.zhihu.service;

import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private AnswerMapper answerMapper;

    private Question question;

    private List<Answer> answers = new ArrayList<>();

    @BeforeEach
    public void setup() {
        question = QuestionFactory.createPushlishedQuestion();

        Answer answer1 = AnswerFactory.createAnswer(question.getId());
        Answer answer2 = AnswerFactory.createAnswer(question.getId());

        answers.add(answer1);
        answers.add(answer2);

    }

    @Test
    void get_existed_question_by_show_method() {
        // given
        given(questionMapper.selectByPrimaryKey(1L)).willReturn(this.question);

        // when
        Question existedQuestion = questionService.show(1L);

        // then
        assertThat(existedQuestion).isNotNull();
        assertThat(existedQuestion.getId()).isEqualTo(this.question.getId());
        assertThat(existedQuestion.getUserId()).isEqualTo(this.question.getUserId());
        assertThat(existedQuestion.getTitle()).isEqualTo(this.question.getTitle());
        assertThat(existedQuestion.getContent()).isEqualTo(this.question.getContent());
    }

    @Test
    void get_not_existed_question_by_show_method() {
        // given
        given(questionMapper.selectByPrimaryKey(1L)).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1L);
        }).isInstanceOf(QuestionNotExistedException.class)
                .hasMessageStartingWith("question not exist");
    }


    @Test
    void a_question_has_many_answers() {
        // given
        given(answerMapper.selectByQuestionId(1L)).willReturn(this.answers);

        // when
        List<Answer> results = questionService.answers(1L);

        // then
        assertThat(results.size()).isEqualTo(this.answers.size());
    }

    @Test
    void can_get_published_question_by_show_method() {
        Question pushlishedQuestion = QuestionFactory.createPushlishedQuestion();
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(pushlishedQuestion);

        // when
        Question existedQuestion = questionService.show(1L);

        // then
        assertThat(existedQuestion).isNotNull();
        assertThat(existedQuestion.getId()).isEqualTo(this.question.getId());
        assertThat(existedQuestion.getUserId()).isEqualTo(this.question.getUserId());
        assertThat(existedQuestion.getTitle()).isEqualTo(this.question.getTitle());
        assertThat(existedQuestion.getContent()).isEqualTo(this.question.getContent());
    }

    @Test
    void get_exception_if_try_get_not_published_question_by_show_method() {
        Question unpushlishedQuestion = QuestionFactory.createUnpushlishedQuestion();
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(unpushlishedQuestion);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1L);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageStartingWith("question not publish");
    }
}