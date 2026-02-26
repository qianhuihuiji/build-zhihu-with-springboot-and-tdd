package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020 -08-24 22:34
 */
public interface AnswerService {

    /**
     * Answers page info.
     *
     * @param questionId the question id
     * @param pageNow    the page now
     * @param pageSize   the page size
     * @return the page info
     */
    PageInfo<Answer> answers(Integer questionId, int pageNow, int pageSize);

    /**
     * Show answer.
     *
     * @param id the id
     * @return the answer
     */
    Answer show(Integer id);

    /**
     * Store.
     *
     * @param questionId  the question id
     * @param answerDto   the answer dto
     * @param accountUser the account user
     */
    void store(Integer questionId, AnswerDto answerDto, AccountUser accountUser);

    /**
     * Mark as best.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     */
    void markAsBest(Integer answerId, AccountUser accountUser);

    /**
     * Destroy.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     */
    void destroy(Integer answerId, AccountUser accountUser);

    /**
     * Is voted up boolean.
     *
     * @param answerId the answer id
     * @return the boolean
     */
    Boolean isVotedUp(Integer answerId);

    /**
     * Up votes count integer.
     *
     * @param answerId the answer id
     * @return the integer
     */
    Integer upVotesCount(Integer answerId);

    /**
     * Is voted down boolean.
     *
     * @param answerId the answer id
     * @return the boolean
     */
    Boolean isVotedDown(Integer answerId);

    /**
     * Down votes count integer.
     *
     * @param answerId the answer id
     * @return the integer
     */
    Integer downVotesCount(Integer answerId);

    /**
     * Gets resource type.
     *
     * @return the resource type
     */
    String getResourceType();
}
