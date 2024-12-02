package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PublishedQuestionController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class SubscribeQuestionController {

    private final QuestionSubscribeService questionSubscribeService;

    @PostMapping("/questions/{questionId}/subscriptions")
    public CommonResult store(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        questionSubscribeService.subscribe(questionId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/questions/{questionId}/subscriptions")
    public CommonResult destroy(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        questionSubscribeService.unsubscribe(questionId, accountUser);
        return CommonResult.success(null);
    }
}
