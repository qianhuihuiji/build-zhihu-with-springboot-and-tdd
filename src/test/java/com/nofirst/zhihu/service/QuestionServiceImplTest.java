package com.nofirst.zhihu.service;

import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.impl.QuestionServiceImpl;
import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
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
        Date now = new Date();
        question = Question.builder()
                .id(1L)
                .userId(1)
                .title("title")
                .content("content")
                .publishedAt(now)
                .build();
        Answer answer1 = new Answer();
        answer1.setId(1L);
        answer1.setQuestionId(1L);
        answer1.setUserId(1);
        answer1.setCreatedAt(now);
        answer1.setUpdatedAt(now);
        answer1.setContent("this is a answer");
        Answer answer2 = new Answer();
        answer2.setId(2L);
        answer2.setQuestionId(1L);
        answer2.setUserId(1);
        answer2.setCreatedAt(now);
        answer2.setUpdatedAt(now);
        answer2.setContent("this is another answer");
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
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);
        question.setPublishedAt(lastWeek);
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(question);

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
        this.question.setPublishedAt(null);
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(this.question);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1L);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageStartingWith("question not publish");
    }
}