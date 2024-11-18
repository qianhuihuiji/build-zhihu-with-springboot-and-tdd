package com.nofirst.zhihu.matcher;

import com.nofirst.zhihu.mbg.model.Question;
import org.mockito.ArgumentMatcher;

public class QuestionMatcher implements ArgumentMatcher<Question> {

    private Question left;

    public QuestionMatcher(Question left) {
        this.left = left;
    }

    // constructors

    @Override
    public boolean matches(Question right) {
        return left.getId().equals(right.getId()) &&
                left.getContent().equals(right.getContent()) &&
                left.getUserId().equals(right.getUserId());
    }
}
