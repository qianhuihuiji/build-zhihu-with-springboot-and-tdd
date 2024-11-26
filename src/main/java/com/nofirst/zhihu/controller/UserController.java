package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.UserLoginDto;
import com.nofirst.zhihu.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf-8")
@AllArgsConstructor
public class UserController {

    private JwtUtil jwtUtil;
    private UserMapper userMapper;

    @PostMapping("/login")
    public CommonResult login(@RequestBody @Validated UserLoginDto userLoginDTO, HttpServletResponse response) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return CommonResult.error("用户名不存在");
        }

        if (!user.getPassword().equals(password)) {
            return CommonResult.error("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(username);
        response.setHeader(JwtUtil.HEADER, token);
        response.setHeader("Access-control-Expost-Headers", JwtUtil.HEADER);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return CommonResult.success(map);
    }

    //@PreAuthorize配合@EnableGlobalMethodSecurity(prePostEnabled = true)使用
    //@PreAuthorize("hasAuthority('/user/logout')")
    @GetMapping("/logout")
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {
        // 退出登录
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //清除认证
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return CommonResult.success(null);
    }
}