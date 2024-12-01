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
    Answer show(Integer id);

    void store(Integer questionId, AnswerDto answerDto, AccountUser accountUser);

    void markAsBest(Integer answerId, AccountUser accountUser);

    void destroy(Integer answerId, AccountUser accountUser);

    Boolean isVotedUp(Integer answerId);

    Integer upVotesCount(Integer answerId);

    Boolean isVotedDown(Integer answerId);

    Integer downVotesCount(Integer answerId);

    String getResourceType();
}
