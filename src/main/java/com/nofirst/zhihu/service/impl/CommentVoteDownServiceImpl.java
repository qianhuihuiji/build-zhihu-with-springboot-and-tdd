package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.CommentVoteDownService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class CommentVoteDownServiceImpl implements CommentVoteDownService {

    private VoteMapper voteMapper;
    private VoteDao voteDao;

    @Override
    public void store(Integer commentId, AccountUser accountUser) {
        int count = voteDao.countByVotedId(commentId, Comment.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode());
        if (count == 0) {
            Vote vote = new Vote();
            vote.setUserId(accountUser.getUserId());
            vote.setVotedId(commentId);
            vote.setResourceType(Comment.class.getSimpleName());
            vote.setActionType(VoteActionType.VOTE_DOWN.getCode());
            Date now = new Date();
            vote.setCreatedAt(now);
            vote.setUpdatedAt(now);

            voteMapper.insert(vote);
        }
    }

    @Override
    public void destroy(Integer commentId, AccountUser accountUser) {
        voteDao.deleteByVotedId(commentId, Comment.class.getSimpleName(), VoteActionType.VOTE_DOWN.getCode());
    }
}
