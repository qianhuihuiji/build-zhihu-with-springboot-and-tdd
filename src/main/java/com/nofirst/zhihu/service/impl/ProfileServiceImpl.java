package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.vo.ProfileVo;
import com.nofirst.zhihu.model.vo.UserVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Profile service.
 */
@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserMapper userMapper;

    @Override
    public ProfileVo show(Integer userId, AccountUser accountUser) {
        User user = userMapper.selectByPrimaryKey(userId);
        ProfileVo profileVo = new ProfileVo();
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setPhone(user.getPhone());
        userVo.setEmail(user.getEmail());
        profileVo.setUser(userVo);

        return profileVo;
    }
}
