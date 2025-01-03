package com.nofirst.zhihu.controller;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.CommentDto;
import com.nofirst.zhihu.model.vo.CommentVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.AnswerCommentService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * Index common result.
     *
     * @param answerId  the answer id
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the common result
     */
    @GetMapping("/answers/{answerId}/comments")
    public CommonResult<PageInfo<CommentVo>> index(@PathVariable Integer answerId,
                                                   @RequestParam @NotNull Integer pageIndex,
                                                   @RequestParam @NotNull Integer pageSize) {
        PageInfo<CommentVo> pageInfo = answerCommentService.index(answerId, pageIndex, pageSize);
        return CommonResult.success(pageInfo);
    }

    /**
     * Store common result.
     *
     * @param answerId    the answer id
     * @param commentDto  the comment dto
     * @param accountUser the account user
     * @return the common result
     */
    @PostMapping("/answers/{answerId}/comments")
    public CommonResult store(@PathVariable Integer answerId,
                              @Validated @RequestBody CommentDto commentDto,
                              @AuthenticationPrincipal AccountUser accountUser) {
        answerCommentService.comment(answerId, commentDto, accountUser);
        return CommonResult.success(null);
    }
}
