package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.CommentVoteUpService;
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
public class CommentUpVoteController {

    private final CommentVoteUpService commentVoteUpService;

    @PostMapping("/comments/{answerId}/up-votes")
    public CommonResult store(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        commentVoteUpService.store(answerId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/comments/{answerId}/up-votes")
    public CommonResult destroy(@PathVariable Integer answerId, @AuthenticationPrincipal AccountUser accountUser) {
        commentVoteUpService.destroy(answerId, accountUser);
        return CommonResult.success(null);
    }

}
