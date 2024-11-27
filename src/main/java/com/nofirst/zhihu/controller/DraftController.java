package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PublishedQuestionController
 *
 * @author nofirst
 */
@RestController
@Validated
@AllArgsConstructor
public class DraftController {

    private final QuestionService questionService;

    @GetMapping("/drafts")
    public CommonResult store(@AuthenticationPrincipal AccountUser accountUser) {
        List<QuestionVo> questionVos = questionService.drafts(accountUser);
        return CommonResult.success(questionVos);
    }
}
