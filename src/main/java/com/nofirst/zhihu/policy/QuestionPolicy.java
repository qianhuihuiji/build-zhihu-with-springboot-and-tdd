package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.security.AccountUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionPolicy {

    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;


    public boolean isQuestionOwner(Long answerId, AccountUser accountUser) {
//        Answer answer = answerMapper.selectByPrimaryKey(answerId);
//        if (Objects.isNull(answer)) {
//            throw new AnswerNotExistedException();
//        }
//
//        return accountUser.getUserId().equals(answer.getUserId());
        return false;
    }
}
