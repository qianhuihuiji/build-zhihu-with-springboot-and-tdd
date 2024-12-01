package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.security.AccountUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AnswerPolicy {

    private final AnswerMapper answerMapper;

    public boolean canDelete(Integer answerId, AccountUser accountUser) {
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        if (Objects.isNull(answer)) {
            throw new AnswerNotExistedException();
        }
        return accountUser.getUserId().equals(answer.getUserId());
    }
}
