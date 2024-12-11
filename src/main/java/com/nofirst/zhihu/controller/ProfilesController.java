package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.vo.ProfileVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProfilesController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class ProfilesController {

    private final ProfileService profileService;

    @GetMapping("/profiles/{userId}")
    public CommonResult<ProfileVo> show(@PathVariable Integer userId, @AuthenticationPrincipal AccountUser accountUser) {
        ProfileVo profileVo = profileService.show(userId, accountUser);
        return CommonResult.success(profileVo);
    }

}
