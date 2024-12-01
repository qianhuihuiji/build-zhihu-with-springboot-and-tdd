package com.nofirst.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.QuestionFilter;
import com.nofirst.zhihu.dao.AnswerDao;
import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;
    private QuestionMapper questionMapper;
    private QuestionDao questionDao;
    private AnswerMapper answerMapper;
    private AnswerDao answerDao;
    private VoteMapper voteMapper;
    private CustomEventPublisher invitedEventPublisher;
    private QuestionFilter questionFilter;


    @Override
    public QuestionVo show(Integer id) {
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
    public PageInfo<Answer> answers(Integer questionId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Answer> answers = answerDao.selectByQuestionId(questionId);
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
        question.setAnswersCount(0);

        questionMapper.insert(question);
    }

    @Override
    public void publish(Integer questionId) {
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
    public PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize, String slug, String by, Integer popularity, Integer unanswered) {
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andPublishedAtIsNotNull();
        Map<String, Object> condition = new HashMap<>();
        condition.put("slug", slug);
        condition.put("by", by);
        condition.put("popularity", popularity);
        condition.put("unanswered", unanswered);
        questionFilter.apply(condition, example, criteria);

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
            questionVo.setAnswersCount(question.getAnswersCount());
            result.add(questionVo);
        }
        PageInfo<QuestionVo> pageResult = new PageInfo<>();
        pageResult.setTotal(questionPageInfo.getTotal());
        pageResult.setPageNum(questionPageInfo.getPageNum());
        pageResult.setPageSize(questionPageInfo.getPageSize());
        pageResult.setList(result);
        return pageResult;
    }

    @Override
    public Boolean isVotedUp(Long answerId) {
        return voteMapper.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_UP.getCode()) > 0;
    }

    @Override
    public Integer upVotesCount(Long answerId) {
        return voteMapper.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_UP.getCode());
    }

    @Override
    public Boolean isVotedDown(Long answerId) {
        return voteMapper.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_DOWN.getCode()) > 0;
    }

    @Override
    public Integer downVotesCount(Long answerId) {
        return voteMapper.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_DOWN.getCode());
    }

    @Override
    public String getResourceType() {
        return Question.class.getSimpleName();
    }
}
