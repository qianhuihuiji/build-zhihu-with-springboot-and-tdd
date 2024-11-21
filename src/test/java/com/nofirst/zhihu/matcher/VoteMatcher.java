package com.nofirst.zhihu.matcher;

import com.nofirst.zhihu.mbg.model.Vote;
import org.mockito.ArgumentMatcher;

public class VoteMatcher implements ArgumentMatcher<Vote> {

    private Vote left;

    public VoteMatcher(Vote left) {
        this.left = left;
    }

    // constructors

    @Override
    public boolean matches(Vote right) {
        return left.getUserId().equals(right.getUserId())
                && left.getVotedId().equals(right.getVotedId())
                && left.getResourceType().equals(right.getResourceType())
                && left.getActionType().equals(right.getActionType());
    }
}
