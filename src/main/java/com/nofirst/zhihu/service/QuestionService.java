package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.model.vo.QuestionVo;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface QuestionService {
    QuestionVo show(Long id);

    PageInfo<Answer> answers(Long questionId, int pageNow, int pageSize);
}
