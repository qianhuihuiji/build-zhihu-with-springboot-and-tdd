package com.nofirst.zhihu.matcher;

import com.nofirst.zhihu.mbg.model.Answer;
import org.mockito.ArgumentMatcher;

public class AnswerMatcher implements ArgumentMatcher<Answer> {

    private Answer left;

    public AnswerMatcher(Answer left) {
        this.left = left;
    }

    // constructors

    @Override
    public boolean matches(Answer right) {
        return left.getQuestionId().equals(right.getQuestionId()) &&
                left.getContent().equals(right.getContent()) &&
                left.getUserId().equals(right.getUserId());
    }
}
