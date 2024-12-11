package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.dto.UserCountDto;

import java.util.Date;
import java.util.List;

public interface AnswerDao {

    List<Answer> selectByQuestionId(Integer questionId);

    List<Answer> selectByUserId(Long userId);

    List<UserCountDto> count(Date beginTime);
}
