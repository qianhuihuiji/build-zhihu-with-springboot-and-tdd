package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.vo.QuestionVo;
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

    @Mock
    private UserMapper userMapper;

    private Question question;

    private List<Answer> answers = new ArrayList<>();

    @BeforeEach
    public void setup() {
        question = QuestionFactory.createPublishedQuestion();

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
        QuestionVo existedQuestion = questionService.show(1L);

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
        PageInfo<Answer> answersPage = questionService.answers(1L, 1, 10);
        List<Answer> results = answersPage.getList();

        // then
        assertThat(answersPage.getTotal()).isEqualTo(2);
        assertThat(results.size()).isEqualTo(this.answers.size());
    }

    @Test
    void can_get_published_question_by_show_method() {
        Question pushlishedQuestion = QuestionFactory.createPublishedQuestion();
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(pushlishedQuestion);

        // when
        QuestionVo existedQuestion = questionService.show(1L);

        // then
        assertThat(existedQuestion).isNotNull();
        assertThat(existedQuestion.getId()).isEqualTo(this.question.getId());
        assertThat(existedQuestion.getUserId()).isEqualTo(this.question.getUserId());
        assertThat(existedQuestion.getTitle()).isEqualTo(this.question.getTitle());
        assertThat(existedQuestion.getContent()).isEqualTo(this.question.getContent());
    }

    @Test
    void get_exception_if_try_get_not_published_question_by_show_method() {
        Question unpushlishedQuestion = QuestionFactory.createUnpublishedQuestion();
        when(this.questionMapper.selectByPrimaryKey(1L)).thenReturn(unpushlishedQuestion);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1L);
        }).isInstanceOf(QuestionNotPublishedException.class)
                .hasMessageStartingWith("question not publish");
    }

    @Test
    void a_question_belongs_to_a_creator() {
        // given
        User user = UserFactory.createUser();
        given(userMapper.selectByPrimaryKey(1)).willReturn(user);

        // when
        User result = questionService.owner(1);

        // then
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getName()).isEqualTo(user.getName());
    }
}