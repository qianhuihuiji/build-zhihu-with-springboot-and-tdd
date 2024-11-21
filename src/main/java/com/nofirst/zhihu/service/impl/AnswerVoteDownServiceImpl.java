package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import com.nofirst.zhihu.service.AnswerVoteDownService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AnswerVoteDownServiceImpl implements AnswerVoteDownService {

    private VoteMapper voteMapper;
    private AnswerService answerService;

    @Override
    public void store(Long answerId, AccountUser accountUser) {
        int count = voteMapper.countByVotedId(answerId, answerService.getResourceType(), VoteActionType.VOTE_DOWN.getCode());
        if (count == 0) {
            Vote vote = new Vote();
            vote.setUserId(accountUser.getUserId());
            vote.setVotedId(answerId);
            vote.setResourceType(answerService.getResourceType());
            vote.setActionType(VoteActionType.VOTE_DOWN.getCode());
            Date now = new Date();
            vote.setCreatedAt(now);
            vote.setUpdatedAt(now);

            voteMapper.insert(vote);
        }
    }

    @Override
    public void destroy(Long answerId, AccountUser accountUser) {
        voteMapper.deleteByVotedId(answerId, answerService.getResourceType(), VoteActionType.VOTE_DOWN.getCode());
    }
}
