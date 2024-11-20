package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Vote;

import java.util.Date;

public class VoteFactory {

    public static Vote createVoteUp() {
        Date now = new Date();
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setUserId(1);
        vote.setVotedId(1L);
        vote.setVotedType("up");
        vote.setCreatedAt(now);
        vote.setUpdatedAt(now);

        return vote;
    }

}
