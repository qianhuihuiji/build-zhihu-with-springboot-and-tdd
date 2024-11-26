package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import com.nofirst.zhihu.validator.ValidCategory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@Validated
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @GetMapping("/questions/{id}")
    public QuestionVo show(@PathVariable Long id) {
        return questionService.show(id);
    }

    @PostMapping("/questions")
    public CommonResult store(@RequestBody @Validated @ValidCategory QuestionDto dto, @AuthenticationPrincipal AccountUser accountUser) {
        questionService.store(dto, accountUser);
        return CommonResult.success(null);
    }
}
