package com.nofirst.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final UserMapper userMapper;
    private QuestionMapper questionMapper;
    private QuestionDao questionDao;
    private AnswerMapper answerMapper;
    private CustomEventPublisher invitedEventPublisher;


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
    public PageInfo<Answer> answers(Long questionId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Answer> answers = answerMapper.selectByQuestionId(questionId);
        return new PageInfo<>(answers);
    }

    @Override
    public User owner(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void store(QuestionDto dto, AccountUser accountUser) {
        Question question = new Question();
        question.setUserId(accountUser.getUserId());
        question.setTitle(dto.getTitle());
        question.setContent(dto.getContent());
        question.setCategoryId(dto.getCategoryId());

        questionMapper.insert(question);
    }

    @Override
    public void publish(Long questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);

        questionDao.publish(questionId, new Date());

        invitedEventPublisher.addPublishQuestionEvent(question);
    }


    @Override
    public List<QuestionVo> drafts(AccountUser accountUser) {
        List<QuestionVo> result = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(accountUser.getUserId());
        criteria.andPublishedAtIsNull();
        List<Question> questions = questionMapper.selectByExample(example);
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setId(question.getId());
            questionVo.setUserId(question.getUserId());
            questionVo.setBestAnswerId(question.getBestAnswerId());
            questionVo.setTitle(question.getTitle());
            questionVo.setContent(question.getContent());
            questionVo.setPublishedAt(question.getPublishedAt());
            result.add(questionVo);
        }

        return result;
    }

    @Override
    public PageInfo<QuestionVo> index(String slug, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andPublishedAtIsNotNull();
        List<Question> questions = questionMapper.selectByExample(example);
        List<QuestionVo> result = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setId(question.getId());
            questionVo.setUserId(question.getUserId());
            questionVo.setBestAnswerId(question.getBestAnswerId());
            questionVo.setTitle(question.getTitle());
            questionVo.setContent(question.getContent());
            questionVo.setPublishedAt(question.getPublishedAt());
            result.add(questionVo);
        }
        return new PageInfo<>(result);
    }
}
