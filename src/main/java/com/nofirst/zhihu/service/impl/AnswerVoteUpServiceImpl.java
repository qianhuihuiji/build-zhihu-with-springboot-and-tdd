package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteType;
import com.nofirst.zhihu.model.enums.VoteUpType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerUpVoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AnswerVoteUpServiceImpl implements AnswerUpVoteService {

    private VoteMapper voteMapper;

    @Override
    public void store(Long answerId, AccountUser accountUser) {
        Vote vote = new Vote();
        vote.setUserId(accountUser.getUserId());
        vote.setVotedId(answerId);
        vote.setVotedType(VoteType.ANSWER.getCode());
        vote.setType(VoteUpType.VOTE_UP.getCode());
        Date now = new Date();
        vote.setCreatedAt(now);
        vote.setUpdatedAt(now);

        voteMapper.insert(vote);
    }

    @Override
    public void destroy(Long answerId, AccountUser accountUser) {
        voteMapper.deleteByVotedId(answerId, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
    }
}
