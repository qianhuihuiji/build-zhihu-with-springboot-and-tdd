package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;

    @Override
    public Answer show(Long id) {
        return answerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void store(Long questionId, Answer answer) {
        // todo: 这里没有设置主键，测试理论上会报错，需要靠mapper层测试来进行保证
        answer.setQuestionId(questionId);

        answerMapper.insert(answer);
    }
}
