package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.CommentVoteDownService;
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
public class CommentDownVoteController {

    private final CommentVoteDownService commentVoteDownService;

    @PostMapping("/comments/{commentId}/down-votes")
    public CommonResult store(@PathVariable Integer commentId, @AuthenticationPrincipal AccountUser accountUser) {
        commentVoteDownService.store(commentId, accountUser);
        return CommonResult.success(null);
    }

    @DeleteMapping("/comments/{commentId}/down-votes")
    public CommonResult destroy(@PathVariable Integer commentId, @AuthenticationPrincipal AccountUser accountUser) {
        commentVoteDownService.destroy(commentId, accountUser);
        return CommonResult.success(null);
    }

}
