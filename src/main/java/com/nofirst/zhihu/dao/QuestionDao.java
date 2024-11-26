package com.nofirst.zhihu.dao;

import java.util.Date;

public interface QuestionDao {

    void markAsBestAnswer(Long questionId, Long answerId);

    void publish(Long questionId, Date publishedAt);
}
