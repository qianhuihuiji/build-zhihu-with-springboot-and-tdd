package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.dto.UserCountDto;

import java.util.Date;
import java.util.List;

/**
 * The interface Answer dao.
 */
public interface AnswerDao {

    /**
     * Select by question id list.
     *
     * @param questionId the question id
     * @return the list
     */
    List<Answer> selectByQuestionId(Integer questionId);

    /**
     * Select by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Answer> selectByUserId(Long userId);

    /**
     * Count list.
     *
     * @param beginTime the begin time
     * @return the list
     */
    List<UserCountDto> count(Date beginTime);
}
