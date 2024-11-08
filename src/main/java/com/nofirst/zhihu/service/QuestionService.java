package com.nofirst.zhihu.service;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;

import java.util.List;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface QuestionService {
    Question show(Long id);

    List<Answer> answers(Long questionId);
}
