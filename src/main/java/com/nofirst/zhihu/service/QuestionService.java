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

    /**
     * Show question vo.
     *
     * @param id the id
     * @return the question vo
     */
    QuestionVo show(Integer id);

    /**
     * Answers page info.
     *
     * @param questionId the question id
     * @param pageNow    the page now
     * @param pageSize   the page size
     * @return the page info
     */
    PageInfo<Answer> answers(Integer questionId, int pageNow, int pageSize);

    /**
     * Owner user.
     *
     * @param userId the user id
     * @return the user
     */
    User owner(Integer userId);

    /**
     * Store.
     *
     * @param dto         the dto
     * @param accountUser the account user
     */
    void store(QuestionDto dto, AccountUser accountUser);

    /**
     * Publish.
     *
     * @param questionId the question id
     */
    void publish(Integer questionId);

    /**
     * Drafts list.
     *
     * @param accountUser the account user
     * @return the list
     */
    List<QuestionVo> drafts(AccountUser accountUser);

    /**
     * Index page info.
     *
     * @param pageIndex  the page index
     * @param pageSize   the page size
     * @param slug       the slug
     * @param by         the by
     * @param popularity the popularity
     * @param unanswered the unanswered
     * @return the page info
     */
    PageInfo<QuestionVo> index(Integer pageIndex, Integer pageSize, String slug, String by, Integer popularity, Integer unanswered);

    /**
     * Is voted up boolean.
     *
     * @param questionId the question id
     * @return the boolean
     */
    Boolean isVotedUp(Integer questionId);

    /**
     * Up votes count integer.
     *
     * @param questionId the question id
     * @return the integer
     */
    Integer upVotesCount(Integer questionId);

    /**
     * Is voted down boolean.
     *
     * @param questionId the question id
     * @return the boolean
     */
    Boolean isVotedDown(Integer questionId);

    /**
     * Down votes count integer.
     *
     * @param questionId the question id
     * @return the integer
     */
    Integer downVotesCount(Integer questionId);

    /**
     * Gets resource type.
     *
     * @return the resource type
     */
    String getResourceType();
}
