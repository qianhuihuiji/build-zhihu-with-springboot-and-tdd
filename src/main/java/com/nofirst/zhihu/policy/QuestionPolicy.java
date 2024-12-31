package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.security.AccountUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * The type Question policy.
 */
@Service
@AllArgsConstructor
public class QuestionPolicy {

    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    /**
     * Can mark answer as best boolean.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     * @return the boolean
     */
    public boolean canMarkAnswerAsBest(Integer answerId, AccountUser accountUser) {
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        if (Objects.isNull(answer)) {
            throw new AnswerNotExistedException();
        }
        Question question = questionMapper.selectByPrimaryKey(answer.getQuestionId());
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }
        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException();
        }
        return accountUser.getUserId().equals(question.getUserId());
    }

    /**
     * Is question owner boolean.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     * @return the boolean
     */
    public boolean isQuestionOwner(Integer questionId, AccountUser accountUser) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }

        return accountUser.getUserId().equals(question.getUserId());
    }
}
