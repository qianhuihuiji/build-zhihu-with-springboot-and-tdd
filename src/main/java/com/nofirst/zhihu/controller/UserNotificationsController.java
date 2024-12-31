package com.nofirst.zhihu.controller;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.vo.NotificationVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.UserNotificationService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActiveUserController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class UserNotificationsController {

    private final UserNotificationService notificationService;

    /**
     * Index common result.
     *
     * @param pageIndex   the page index
     * @param pageSize    the page size
     * @param accountUser the account user
     * @return the common result
     */
    @GetMapping("/notifications")
    public CommonResult<PageInfo<NotificationVo>> index(@RequestParam @NotNull Integer pageIndex,
                                                        @RequestParam @NotNull Integer pageSize,
                                                        @AuthenticationPrincipal AccountUser accountUser) {
        PageInfo<NotificationVo> activeUsers = notificationService.index(accountUser.getUserId(), pageIndex, pageSize);
        return CommonResult.success(activeUsers);
    }
}
