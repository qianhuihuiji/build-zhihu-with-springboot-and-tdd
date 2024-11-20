package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerUpVoteService;
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

    private final AnswerUpVoteService answerUpVoteService;

    @PostMapping("/answers/{answerId}/up-votes")
    public CommonResult store(@PathVariable Long answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerUpVoteService.store(answerId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/answers/{answerId}/up-votes")
    public CommonResult destroy(@PathVariable Long answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerUpVoteService.destroy(answerId, accountUser);
        return CommonResult.success(null);
    }

}
