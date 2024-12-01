package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Vote;

public interface VoteDao {

    Vote selectByVotedId(Integer voteId, String resourceType, String actionType);

    int countByVotedId(Integer voteId, String resourceType, String actionType);

    void deleteByVotedId(Integer votedId, String resourceType, String actionType);
}
