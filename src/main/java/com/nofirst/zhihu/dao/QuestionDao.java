package com.nofirst.zhihu.dao;

import java.util.Date;

public interface QuestionDao {

    void markAsBestAnswer(Integer questionId, Integer answerId);

    void publish(Integer questionId, Date publishedAt);
}
