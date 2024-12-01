package com.nofirst.zhihu.service;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface AnswerService {
    Answer show(Long id);

    void store(Integer questionId, AnswerDto answerDto, AccountUser accountUser);

    void markAsBest(Long answerId, AccountUser accountUser);

    void destroy(Long answerId, AccountUser accountUser);

    Boolean isVotedUp(Long answerId);

    Integer upVotesCount(Long answerId);

    Boolean isVotedDown(Long answerId);

    Integer downVotesCount(Long answerId);

    String getResourceType();
}
