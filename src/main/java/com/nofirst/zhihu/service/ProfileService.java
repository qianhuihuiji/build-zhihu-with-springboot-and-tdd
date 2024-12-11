package com.nofirst.zhihu.service;

import com.nofirst.zhihu.model.vo.ProfileVo;
import com.nofirst.zhihu.security.AccountUser;

/**
 * ProfileService
 *
 * @author nofirst
 */
public interface ProfileService {

    ProfileVo show(Integer userId, AccountUser accountUser);
}
