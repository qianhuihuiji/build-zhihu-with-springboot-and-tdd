package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import com.nofirst.zhihu.service.AnswerVoteUpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * The type Answer vote up service.
 */
@Service
@AllArgsConstructor
public class AnswerVoteUpServiceImpl implements AnswerVoteUpService {

    private VoteMapper voteMapper;
    private VoteDao voteDao;
    private AnswerService answerService;

    @Override
    public void store(Integer answerId, AccountUser accountUser) {
        int count = voteDao.countByVotedId(answerId, answerService.getResourceType(), VoteActionType.VOTE_UP.getCode());
        if (count == 0) {
            Vote vote = new Vote();
            vote.setUserId(accountUser.getUserId());
            vote.setVotedId(answerId);
            vote.setResourceType(answerService.getResourceType());
            vote.setActionType(VoteActionType.VOTE_UP.getCode());
            Date now = new Date();
            vote.setCreatedAt(now);
            vote.setUpdatedAt(now);

            voteMapper.insert(vote);
        }
    }

    @Override
    public void destroy(Integer answerId, AccountUser accountUser) {
        voteDao.deleteByVotedId(answerId, answerService.getResourceType(), VoteActionType.VOTE_UP.getCode());
    }
}
