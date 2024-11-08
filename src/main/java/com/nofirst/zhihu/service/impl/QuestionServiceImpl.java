package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private QuestionMapper questionMapper;
    private AnswerMapper answerMapper;

    @Override
    public Question show(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException("question not exist");
        }
        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException("question not publish");
        }
        return question;
    }

    @Override
    public List<Answer> answers(Long questionId) {
        return answerMapper.selectByQuestionId(questionId);
    }
}
