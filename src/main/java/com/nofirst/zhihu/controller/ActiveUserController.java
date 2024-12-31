package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.vo.UserVo;
import com.nofirst.zhihu.task.ActiveUserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ActiveUserController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class ActiveUserController {

    private final ActiveUserService activeUserService;

    /**
     * Index common result.
     *
     * @return the common result
     */
    @GetMapping("/active-users")
    public CommonResult<List<UserVo>> index() {
        List<UserVo> activeUsers = activeUserService.getActiveUsers();
        return CommonResult.success(activeUsers);
    }
}
