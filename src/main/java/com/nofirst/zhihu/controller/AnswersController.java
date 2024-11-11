package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.model.dto.AnswerDto;
import com.nofirst.zhihu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionsController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
@AllArgsConstructor
@Validated
public class AnswersController {

    private final AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public void store(@PathVariable Long questionId, @RequestBody @Validated AnswerDto answerDto) {
        answerService.store(questionId, answerDto);
    }
}
