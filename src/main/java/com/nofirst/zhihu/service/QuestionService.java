package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.security.AccountUser;

/**
 * QuestionService
 *
 * @author nofirst
 * @date 2020-08-24 22:34
 */
public interface QuestionService {
    QuestionVo show(Long id);

    PageInfo<Answer> answers(Long questionId, int pageNow, int pageSize);

    User owner(Integer userId);

    void store(QuestionDto dto, AccountUser accountUser);

    void publish(Long questionId);
}
