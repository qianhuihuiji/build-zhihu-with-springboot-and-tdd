package com.nofirst.zhihu.security;

import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new AccountUser(user.getId(), user.getName(), user.getPassword(), getUserAuthority(user.getName()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     *
     * @param username
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(String username) {
        // todo:
        // 角色(比如ROLE_admin)，菜单操作权限(比如sys:user:list)
        // 角色必须以ROLE_开头，security在判断角色时会自动截取ROLE_
        // 比如ROLE_admin,ROLE_normal,sys:user:list,...
        String authority = "";
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
