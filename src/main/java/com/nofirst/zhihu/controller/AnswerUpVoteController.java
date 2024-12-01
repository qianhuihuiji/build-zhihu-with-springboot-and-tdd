package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerVoteUpService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AnswerUpVoteController
 *
 * @author nofirst
 */
@RestController
@AllArgsConstructor
@Validated
public class AnswerUpVoteController {

    private final AnswerVoteUpService answerVoteUpService;

    @PostMapping("/answers/{answerId}/up-votes")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteUpService.store(answerId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/answers/{answerId}/up-votes")
    public CommonResult destroy(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteUpService.destroy(answerId, accountUser);
        return CommonResult.success(null);
    }

}
