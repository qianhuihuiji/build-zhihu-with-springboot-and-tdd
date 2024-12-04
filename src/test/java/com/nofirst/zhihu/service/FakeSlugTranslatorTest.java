package com.nofirst.zhihu.service;

import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FakeSlugTranslatorTest {

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
    @Mock
    private CustomEventPublisher customEventPublisher;

    private Answer defaultAnswer;
    private AnswerDto defaultAnswerDto;

    @BeforeEach
    public void setup() {
        this.defaultAnswer = AnswerFactory.createAnswer(1);
        this.defaultAnswerDto = AnswerFactory.createAnswerDto();
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
}