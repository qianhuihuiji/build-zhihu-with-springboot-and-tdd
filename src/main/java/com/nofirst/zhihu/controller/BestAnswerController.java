package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class BestAnswerController {

    private final AnswerService answerService;

    @PostMapping("/answers/{answerId}/best")
    public CommonResult store(@PathVariable Long answerId, @AuthenticationPrincipal AccountUser accountUser) {
        answerService.markAsBest(answerId);

        return CommonResult.success(null);
    }
}
