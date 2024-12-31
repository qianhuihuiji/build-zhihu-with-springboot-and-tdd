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

    /**
     * Store common result.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     * @return the common result
     */
    @PostMapping("/questions/{questionId}/subscriptions")
    public CommonResult store(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        questionSubscribeService.subscribe(questionId, accountUser);
        return CommonResult.success(null);
    }

    /**
     * Destroy common result.
     *
     * @param questionId  the question id
     * @param accountUser the account user
     * @return the common result
     */
    @DeleteMapping("/questions/{questionId}/subscriptions")
    public CommonResult destroy(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        questionSubscribeService.unsubscribe(questionId, accountUser);
        return CommonResult.success(null);
    }
}
