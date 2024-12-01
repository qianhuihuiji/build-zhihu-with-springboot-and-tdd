package com.nofirst.zhihu.service;

import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.matcher.VoteMatcher;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.impl.AnswerVoteUpServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerVoteUpServiceImplTest {

    @InjectMocks
    private AnswerVoteUpServiceImpl answerVoteUpService;

    @Mock
    private VoteMapper voteMapper;
    @Mock
    private VoteDao voteDao;
    @Mock
    private AnswerService answerService;

    @Test
    void answer_can_vote_up() {
        // given
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        when(answerService.getResourceType()).thenReturn(Answer.class.getSimpleName());
        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerVoteUpService.store(1, accountUser);

        // then
        verify(voteMapper, times(1)).insert(argThat(new VoteMatcher(vote)));
    }

}