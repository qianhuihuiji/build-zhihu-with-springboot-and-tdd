package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
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
public class PublishedQuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions/{questionId}/published-questions")
    @PreAuthorize("@questionPolicy.isQuestionOwner(#questionId, #accountUser)")
    public CommonResult store(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        questionService.publish(questionId);
        return CommonResult.success(null);
    }
}
