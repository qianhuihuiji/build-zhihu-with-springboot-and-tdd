package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Override
    public Answer show(Long id) {
        return answerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void store(Long questionId, AnswerDto answerDto, AccountUser accountUser) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }
        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException();
        }
        Date now = new Date();
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserId(accountUser.getUserId());
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);
        answer.setContent(answerDto.getContent());

        answerMapper.insert(answer);
    }

    @Override
    public void markAsBest(Long answerId) {

    }
}
