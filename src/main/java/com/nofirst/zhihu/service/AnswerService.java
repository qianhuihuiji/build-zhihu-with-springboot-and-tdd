package com.nofirst.zhihu.service;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.dto.AnswerDto;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface AnswerService {
    Answer show(Long id);

    void store(Long questionId, AnswerDto answerDto);
}
