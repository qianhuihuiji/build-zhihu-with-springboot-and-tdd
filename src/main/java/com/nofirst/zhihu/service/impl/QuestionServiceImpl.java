package com.nofirst.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final UserMapper userMapper;
    private QuestionMapper questionMapper;
    private AnswerMapper answerMapper;


    @Override
    public QuestionVo show(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }
        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException();
        }
        QuestionVo questionVo = new QuestionVo();
        questionVo.setId(question.getId());
        questionVo.setUserId(question.getUserId());
        questionVo.setBestAnswerId(question.getBestAnswerId());
        questionVo.setTitle(question.getTitle());
        questionVo.setContent(question.getContent());
        questionVo.setPublishedAt(question.getPublishedAt());
        questionVo.setAnswers(answers(question.getId(), 1, 20));

        return questionVo;
    }

    @Override
    public PageInfo<Answer> answers(Long questionId, int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Answer> answers = answerMapper.selectByQuestionId(questionId);
        return new PageInfo<>(answers);
    }

    @Override
    public User owner(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
