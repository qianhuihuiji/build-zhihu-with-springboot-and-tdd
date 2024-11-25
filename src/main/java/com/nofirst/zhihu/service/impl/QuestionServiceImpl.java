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
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.publisher.YouWereInvitedEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final UserMapper userMapper;
    private QuestionMapper questionMapper;
    private AnswerMapper answerMapper;
    private YouWereInvitedEventPublisher invitedEventPublisher;


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
        Pattern p = Pattern.compile("(?<=@)\\S+");
        Matcher m = p.matcher(question.getContent());
        while (m.find()) {
            String username = m.group();
            User user = userMapper.selectByUsername(username);
            if (Objects.nonNull(user)) {
                invitedEventPublisher.publishEvent(question, user);
            }
        }
        questionMapper.publish(questionId, new Date());
    }
}
