package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.mbg.model.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> selectByQuestionId(Integer questionId);

    List<Answer> selectByUserId(Long userId);
}
