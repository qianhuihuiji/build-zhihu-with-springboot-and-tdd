package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerCommentService;
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
public class AnswerCommentsController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("/answers/{answerId}/comments")
    public CommonResult store(@PathVariable Integer answerId,
                              @Validated @RequestBody CommentDto commentDto,
                              @AuthenticationPrincipal AccountUser accountUser) {
        answerCommentService.comment(answerId, commentDto, accountUser);
        return CommonResult.success(null);
    }
}
