package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.security.AccountUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * The type Answer policy.
 */
@Service
@AllArgsConstructor
public class AnswerPolicy {

    private final AnswerMapper answerMapper;

    /**
     * Can delete boolean.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     * @return the boolean
     */
    public boolean canDelete(Integer answerId, AccountUser accountUser) {
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        if (Objects.isNull(answer)) {
            throw new AnswerNotExistedException();
        }
        return accountUser.getUserId().equals(answer.getUserId());
    }
}
