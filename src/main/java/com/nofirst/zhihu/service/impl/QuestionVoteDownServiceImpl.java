package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import com.nofirst.zhihu.service.QuestionVoteDownService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class QuestionVoteDownServiceImpl implements QuestionVoteDownService {

    private VoteMapper voteMapper;
    private QuestionService questionService;

    @Override
    public void store(Long questionId, AccountUser accountUser) {
        int count = voteMapper.countByVotedId(questionId, questionService.getResourceType(), VoteActionType.VOTE_DOWN.getCode());
        if (count == 0) {
            Vote vote = new Vote();
            vote.setUserId(accountUser.getUserId());
            vote.setVotedId(questionId);
            vote.setResourceType(questionService.getResourceType());
            vote.setActionType(VoteActionType.VOTE_DOWN.getCode());
            Date now = new Date();
            vote.setCreatedAt(now);
            vote.setUpdatedAt(now);

            voteMapper.insert(vote);
        }
    }

    @Override
    public void destroy(Long questionId, AccountUser accountUser) {
        voteMapper.deleteByVotedId(questionId, questionService.getResourceType(), VoteActionType.VOTE_DOWN.getCode());
    }
}
