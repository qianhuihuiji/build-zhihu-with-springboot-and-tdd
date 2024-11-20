package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Vote;

import java.util.Date;

public class VoteFactory {

    public static Vote createVoteUp(String votedType, String type) {
        Date now = new Date();
        Vote vote = new Vote();
        vote.setUserId(1);
        vote.setVotedId(1L);
        vote.setVotedType(votedType);
        vote.setType(type);
        vote.setCreatedAt(now);
        vote.setUpdatedAt(now);

        return vote;
    }

}
