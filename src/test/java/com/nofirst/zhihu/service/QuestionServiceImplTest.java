package com.nofirst.zhihu.service;

import com.nofirst.zhihu.dao.AnswerDao;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
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
    private AnswerDao answerDao;

    @Mock
    private VoteDao voteDao;

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
        given(questionMapper.selectByPrimaryKey(1)).willReturn(this.question);

        // when
        QuestionVo existedQuestion = questionService.show(1);

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
        given(questionMapper.selectByPrimaryKey(1)).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1);
        }).isInstanceOf(QuestionNotExistedException.class)
                .hasMessageStartingWith("question not exist");
    }

    @Test
    void can_get_published_question_by_show_method() {
        Question pushlishedQuestion = QuestionFactory.createPublishedQuestion();
        when(this.questionMapper.selectByPrimaryKey(1)).thenReturn(pushlishedQuestion);

        // when
        QuestionVo existedQuestion = questionService.show(1);

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
        when(this.questionMapper.selectByPrimaryKey(1)).thenReturn(unpushlishedQuestion);

        // then
        assertThatThrownBy(() -> {
            // when
            questionService.show(1);
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

    @Test
    void it_can_detect_all_invited_users() {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        question.setContent("@Jane @Foo please help me");

        // when
        List<String> invitedUsers = question.invitedUsers();

        // then
        assertThat(Arrays.asList("Jane", "Foo")).isEqualTo(invitedUsers);
    }

    @Test
    void question_can_know_it_is_voted_up() {
        // given
        Integer questionId = 1;
        given(voteDao.countByVotedId(questionId, Question.class.getSimpleName(), VoteActionType.VOTE_UP.getCode())).willReturn(1);

        // when
        Boolean votedUp = questionService.isVotedUp(questionId);

        // then
        assertThat(votedUp).isTrue();
    }

    @Test
    void question_can_know_up_votes_count() {
        // given
        Integer questionId = 1;
        given(voteDao.countByVotedId(questionId, Question.class.getSimpleName(), VoteActionType.VOTE_UP.getCode())).willReturn(1);

        // when
        Integer votedUpCount = questionService.upVotesCount(questionId);

        // then
        assertThat(votedUpCount).isEqualTo(1);
    }


    @Test
    void question_can_know_it_is_voted_down() {
        // given
        Integer questionId = 1;
        given(voteDao.countByVotedId(questionId, Question.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode())).willReturn(1);

        // when
        Boolean votedUp = questionService.isVotedDown(questionId);

        // then
        assertThat(votedUp).isTrue();
    }

    @Test
    void question_can_know_down_votes_count() {
        // given
        Integer questionId = 1;
        given(voteDao.countByVotedId(questionId, Question.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode())).willReturn(1);

        // when
        Integer votedUpCount = questionService.downVotesCount(questionId);

        // then
        assertThat(votedUpCount).isEqualTo(1);
    }
}