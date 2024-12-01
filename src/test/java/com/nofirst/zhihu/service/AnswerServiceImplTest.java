package com.nofirst.zhihu.service;

import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.matcher.AnswerMatcher;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.model.enums.VoteActionType;
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
    @Mock
    private QuestionDao questionDao;
    @Mock
    private VoteMapper voteMapper;
    @Mock
    private VoteDao voteDao;

    private Answer defaultAnswer;
    private AnswerDto defaultAnswerDto;

    @BeforeEach
    public void setup() {
        this.defaultAnswer = AnswerFactory.createAnswer(1);
        this.defaultAnswerDto = AnswerFactory.createAnswerDto();
    }

    @Test
    void get_existed_answer_by_show_method() {
        // given
        given(answerMapper.selectByPrimaryKey(1)).willReturn(this.defaultAnswer);

        // when
        Answer result = answerService.show(1);

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
        publishedQuestion.setId(1);
        given(questionMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(publishedQuestion);

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerService.store(1, this.defaultAnswerDto, accountUser);

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
        Answer answer = AnswerFactory.createAnswer(publishedQuestion.getId());
        publishedQuestion.setBestAnswerId(answer.getId());
        given(questionMapper.selectByPrimaryKey(publishedQuestion.getId())).willReturn(publishedQuestion);
        given(answerMapper.selectByPrimaryKey(answer.getId())).willReturn(answer);

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerService.markAsBest(1, accountUser);

        // then
        verify(questionDao, times(1)).markAsBestAnswer(publishedQuestion.getId(), answer.getId());
        assertThat(answer.isBest(publishedQuestion)).isTrue();
    }

    @Test
    void can_delete_answer() {
        // given

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerService.destroy(1, accountUser);

        // then
        verify(answerMapper, times(1)).deleteByPrimaryKey(1);
    }

    @Test
    void answer_can_know_it_is_voted_up() {
        // given
        Integer answerId = 1;
        given(voteDao.countByVotedId(answerId, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode())).willReturn(1);

        // when
        Boolean votedUp = answerService.isVotedUp(answerId);

        // then
        assertThat(votedUp).isTrue();
    }

    @Test
    void answer_can_know_up_votes_count() {
        // given
        Integer answerId = 1;
        given(voteDao.countByVotedId(answerId, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode())).willReturn(1);

        // when
        Integer votedUpCount = answerService.upVotesCount(answerId);

        // then
        assertThat(votedUpCount).isEqualTo(1);
    }


    @Test
    void answer_can_know_it_is_voted_down() {
        // given
        Integer answerId = 1;
        given(voteDao.countByVotedId(answerId, Answer.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode())).willReturn(1);

        // when
        Boolean votedUp = answerService.isVotedDown(answerId);

        // then
        assertThat(votedUp).isTrue();
    }

    @Test
    void answer_can_know_down_votes_count() {
        // given
        Integer answerId = 1;
        given(voteDao.countByVotedId(answerId, Answer.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode())).willReturn(1);

        // when
        Integer votedUpCount = answerService.downVotesCount(answerId);

        // then
        assertThat(votedUpCount).isEqualTo(1);
    }
}