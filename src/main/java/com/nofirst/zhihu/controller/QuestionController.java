package com.nofirst.zhihu.controller;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.model.dto.QuestionDto;
import com.nofirst.zhihu.model.vo.QuestionVo;
import com.nofirst.zhihu.security.AccountUser;
import com.nofirst.zhihu.service.QuestionService;
import com.nofirst.zhihu.validator.ValidCategory;
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
 * QuestionController
 *
 * @author nofirst
 * @date 2020 -08-24 22:24
 */
@RestController
@Validated
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    /**
     * Show question vo.
     *
     * @param id the id
     * @return the question vo
     */
    @GetMapping("/questions/{id}")
    public QuestionVo show(@PathVariable Integer id) {
        return questionService.show(id);
    }

    /**
     * Store common result.
     *
     * @param dto         the dto
     * @param accountUser the account user
     * @return the common result
     */
    @PostMapping("/questions")
    public CommonResult store(@RequestBody @Validated @ValidCategory QuestionDto dto, @AuthenticationPrincipal AccountUser accountUser) {
        questionService.store(dto, accountUser);
        return CommonResult.success(null);
    }

    /**
     * Index common result.
     *
     * @param pageIndex  the page index
     * @param pageSize   the page size
     * @param slug       the slug
     * @param by         the by
     * @param popularity the popularity
     * @param unanswered the unanswered
     * @return the common result
     */
    @GetMapping("/questions")
    public CommonResult<PageInfo<QuestionVo>> index(@RequestParam @NotNull Integer pageIndex,
                                                    @RequestParam @NotNull Integer pageSize,
                                                    @RequestParam(required = false) String slug,
                                                    @RequestParam(required = false) String by,
                                                    @RequestParam(required = false) Integer popularity,
                                                    @RequestParam(required = false) Integer unanswered) {
        PageInfo<QuestionVo> questionPage = questionService.index(pageIndex, pageSize, slug, by, popularity, unanswered);
        return CommonResult.success(questionPage);
    }
}
