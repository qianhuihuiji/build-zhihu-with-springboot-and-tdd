package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Vote;

import java.util.Date;

public class VoteFactory {

    public static Vote createVote(String resourceType, String actionType) {
        Date now = new Date();
        Vote vote = new Vote();
        vote.setUserId(1);
        vote.setVotedId(1L);
        vote.setResourceType(resourceType);
        vote.setActionType(actionType);
        vote.setCreatedAt(now);
        vote.setUpdatedAt(now);

        return vote;
    }

}
