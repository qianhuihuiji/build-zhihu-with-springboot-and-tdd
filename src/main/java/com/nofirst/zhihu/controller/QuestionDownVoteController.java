package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionVoteDownService;
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
public class QuestionDownVoteController {

    private final QuestionVoteDownService answerVoteDownService;

    @PostMapping("/questions/{questionId}/down-votes")
    public CommonResult store(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteDownService.store(questionId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/questions/{questionId}/down-votes")
    public CommonResult destroy(@PathVariable Integer questionId, @AuthenticationPrincipal AccountUser accountUser) {
        answerVoteDownService.destroy(questionId, accountUser);
        return CommonResult.success(null);
    }

}
