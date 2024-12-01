package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerVoteDownService;
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
public class AnswerDownVoteController {

    private final AnswerVoteDownService answerVoteDownService;

    @PostMapping("/answers/{answerId}/down-votes")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteDownService.store(answerId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/answers/{answerId}/down-votes")
    public CommonResult destroy(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteDownService.destroy(answerId, accountUser);
        return CommonResult.success(null);
    }

}
