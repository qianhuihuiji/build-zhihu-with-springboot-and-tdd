package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotExistedException;
import com.nofirst.zhihu.exception.QuestionNotPublishedException;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.model.enums.VoteActionType;
import com.nofirst.zhihu.publisher.CustomEventPublisher;
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
    private final QuestionDao questionDao;
    private final VoteMapper voteMapper;
    private final VoteDao voteDao;
    private final CustomEventPublisher customEventPublisher;

    @Override
    public Answer show(Integer id) {
        return answerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void store(Integer questionId, AnswerDto answerDto, AccountUser accountUser) {
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

        question.setAnswersCount(question.getAnswersCount() + 1);
        questionMapper.updateByPrimaryKeySelective(question);

        // 给关注者发通知
        customEventPublisher.firePostAnswerEvent(answer, accountUser.getUserId());
    }

    @Override
    public void markAsBest(Integer answerId, AccountUser accountUser) {
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        if (Objects.isNull(answer)) {
            throw new AnswerNotExistedException();
        }
        Question question = questionMapper.selectByPrimaryKey(answer.getQuestionId());
        if (Objects.isNull(question)) {
            throw new QuestionNotExistedException();
        }
        if (Objects.isNull(question.getPublishedAt())) {
            throw new QuestionNotPublishedException();
        }
        questionDao.markAsBestAnswer(question.getId(), answer.getId());
    }

    @Override
    public void destroy(Integer answerId, AccountUser accountUser) {
        answerMapper.deleteByPrimaryKey(answerId);
    }

    @Override
    public Boolean isVotedUp(Integer answerId) {
        return voteDao.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_UP.getCode()) > 0;
    }

    @Override
    public Integer upVotesCount(Integer answerId) {
        return voteDao.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_UP.getCode());
    }

    @Override
    public Boolean isVotedDown(Integer answerId) {
        return voteDao.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_DOWN.getCode()) > 0;
    }

    @Override
    public Integer downVotesCount(Integer answerId) {
        return voteDao.countByVotedId(answerId, getResourceType(), VoteActionType.VOTE_DOWN.getCode());
    }

    @Override
    public String getResourceType() {
        return Answer.class.getSimpleName();
    }
}
