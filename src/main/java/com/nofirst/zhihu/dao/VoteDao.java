package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Vote;

/**
 * The interface Vote dao.
 */
public interface VoteDao {

    /**
     * Select by voted id vote.
     *
     * @param voteId       the vote id
     * @param resourceType the resource type
     * @param actionType   the action type
     * @return the vote
     */
    Vote selectByVotedId(Integer voteId, String resourceType, String actionType);

    /**
     * Count by voted id int.
     *
     * @param voteId       the vote id
     * @param resourceType the resource type
     * @param actionType   the action type
     * @return the int
     */
    int countByVotedId(Integer voteId, String resourceType, String actionType);

    /**
     * Delete by voted id.
     *
     * @param votedId      the voted id
     * @param resourceType the resource type
     * @param actionType   the action type
     */
    void deleteByVotedId(Integer votedId, String resourceType, String actionType);
}
