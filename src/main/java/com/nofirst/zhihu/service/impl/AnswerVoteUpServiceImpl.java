package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
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
        vote.setVotedType("up");
        Date now = new Date();
        vote.setCreatedAt(now);
        vote.setUpdatedAt(now);

        voteMapper.insert(vote);
    }
}
