package com.nofirst.zhihu.service;

import com.nofirst.zhihu.model.vo.ProfileVo;
import com.nofirst.zhihu.security.AccountUser;

/**
 * ProfileService
 *
 * @author nofirst
 */
public interface ProfileService {

    /**
     * Show profile vo.
     *
     * @param userId      the user id
     * @param accountUser the account user
     * @return the profile vo
     */
    ProfileVo show(Integer userId, AccountUser accountUser);
}
