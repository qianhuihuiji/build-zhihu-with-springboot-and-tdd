package com.nofirst.zhihu.dao;

import com.nofirst.zhihu.model.dto.UserCountDto;

import java.util.Date;
import java.util.List;

public interface QuestionDao {

    void markAsBestAnswer(Integer questionId, Integer answerId);

    void publish(Integer questionId, Date publishedAt);

    List<UserCountDto> count(Date beginTime);
}
