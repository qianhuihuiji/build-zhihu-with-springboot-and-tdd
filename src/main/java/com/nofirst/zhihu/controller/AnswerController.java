package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
@AllArgsConstructor
@Validated
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public CommonResult store(@PathVariable Integer questionId, @RequestBody @Validated AnswerDto answerDto, @AuthenticationPrincipal AccountUser accountUser) {

        answerService.store(questionId, answerDto, accountUser);

        return CommonResult.success(null);
    }

    @DeleteMapping("/answers/{answerId}")
    @PreAuthorize("@answerPolicy.canDelete(#answerId, #accountUser)")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {

        answerService.destroy(answerId, accountUser);

        return CommonResult.success(null);
    }
}
