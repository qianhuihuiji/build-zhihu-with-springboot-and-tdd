package com.nofirst.zhihu.dao;

import java.util.Date;

public interface QuestionDao {

    void markAsBestAnswer(Integer questionId, Long answerId);

    void publish(Integer questionId, Date publishedAt);
}
