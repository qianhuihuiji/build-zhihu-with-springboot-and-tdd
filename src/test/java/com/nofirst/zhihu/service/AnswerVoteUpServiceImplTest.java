package com.nofirst.zhihu.service;

import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.matcher.VoteMatcher;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.model.enums.VoteResourceType;
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

@ExtendWith(MockitoExtension.class)
class AnswerVoteUpServiceImplTest {

    @InjectMocks
    private AnswerVoteUpServiceImpl answerVoteUpService;

    @Mock
    private VoteMapper voteMapper;

    @Test
    void can_post_an_answer_to_a_published_question() {
        // given
        Vote vote = VoteFactory.createVote(VoteResourceType.ANSWER.getCode(), VoteActionType.VOTE_UP.getCode());
        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        answerVoteUpService.store(1L, accountUser);

        // then
        verify(voteMapper, times(1)).insert(argThat(new VoteMatcher(vote)));
    }

}