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

    /**
     * Store common result.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     * @return the common result
     */
    @PostMapping("/answers/{answerId}/up-votes")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteUpService.store(answerId, accountUser);
        return CommonResult.success(null);
    }

    /**
     * Destroy common result.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     * @return the common result
     */
    @DeleteMapping("/answers/{answerId}/up-votes")
    public CommonResult destroy(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteUpService.destroy(answerId, accountUser);
        return CommonResult.success(null);
    }

}
