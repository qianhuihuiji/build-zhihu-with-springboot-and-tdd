package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.security.AccountUser;

import java.util.List;

/**
 * QuestionService
 *
 * @author nofirst
 */
public interface QuestionService {

    QuestionVo show(Integer id);

    PageInfo<Answer> answers(Integer questionId, int pageNow, int pageSize);

    User owner(Integer userId);

    void store(QuestionDto dto, AccountUser accountUser);

    void publish(Integer questionId);

    List<QuestionVo> drafts(AccountUser accountUser);

    PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize, String slug, String by, Integer popularity, Integer unanswered);

    Boolean isVotedUp(Integer questionId);

    Integer upVotesCount(Integer questionId);

    Boolean isVotedDown(Integer questionId);

    Integer downVotesCount(Integer questionId);

    String getResourceType();
}
