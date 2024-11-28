package com.nofirst.zhihu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.mbg.model.CategoryExample;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import io.micrometer.common.util.StringUtils;
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
    private final CategoryMapper categoryMapper;
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
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(example);
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
    public PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize, String slug, String by) {
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andPublishedAtIsNotNull();
        appendQueryCondition(slug, by, criteria);
        
        PageHelper.startPage(pageIndex, pageSize);
        List<Question> questions = questionMapper.selectByExample(example);
        // 如果不使用 mapper 返回的对象直接构造分页对象，total会被错误赋值成当前页的数据的数量，而不是总数
        PageInfo<Question> questionPageInfo = new PageInfo<>(questions);
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
        PageInfo<QuestionVo> pageResult = new PageInfo<>();
        pageResult.setTotal(questionPageInfo.getTotal());
        pageResult.setPageNum(questionPageInfo.getPageNum());
        pageResult.setPageSize(questionPageInfo.getPageSize());
        pageResult.setList(result);
        return pageResult;
    }

    private void appendQueryCondition(String slug, String by, QuestionExample.Criteria criteria) {
        if (StringUtils.isNotBlank(by)) {
            User user = userMapper.selectByUsername(by);
            if (Objects.nonNull(user)) {
                criteria.andUserIdEqualTo(user.getId());
            }
        }
        if (StringUtils.isNotBlank(slug)) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andSlugLike(slug);
            List<Category> categories = categoryMapper.selectByExample(categoryExample);
            if (CollectionUtil.isNotEmpty(categories)) {
                criteria.andCategoryIdEqualTo(categories.get(0).getId());
            }
        }
    }
}
