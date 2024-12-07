package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionCommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionCommentsController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class QuestionCommentsController {

    private final QuestionCommentService questionCommentService;

    @PostMapping("/questions/{questionId}/comments")
    public CommonResult store(@PathVariable Integer questionId,
                              @Validated @RequestBody CommentDto commentDto,
                              @AuthenticationPrincipal AccountUser accountUser) {
        questionCommentService.comment(questionId, commentDto, accountUser);
        return CommonResult.success(null);
    }
}
