package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Best answer controller.
 */
@RestController
@AllArgsConstructor
@Validated
public class BestAnswerController {

    private final AnswerService answerService;

    /**
     * Store common result.
     *
     * @param answerId    the answer id
     * @param accountUser the account user
     * @return the common result
     */
    @PostMapping("/answers/{answerId}/best")
    @PreAuthorize("@questionPolicy.canMarkAnswerAsBest(#answerId, #accountUser)")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerService.markAsBest(answerId, accountUser);

        return CommonResult.success(null);
    }
}
