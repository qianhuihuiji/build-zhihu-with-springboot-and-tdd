package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.model.dto.UserCountDto;

import java.util.Date;
import java.util.List;

/**
 * The interface Question dao.
 */
public interface QuestionDao {

    /**
     * Mark as best answer.
     *
     * @param questionId the question id
     * @param answerId   the answer id
     */
    void markAsBestAnswer(Integer questionId, Integer answerId);

    /**
     * Publish.
     *
     * @param questionId  the question id
     * @param publishedAt the published at
     */
    void publish(Integer questionId, Date publishedAt);

    /**
     * Count list.
     *
     * @param beginTime the begin time
     * @return the list
     */
    List<UserCountDto> count(Date beginTime);
}
