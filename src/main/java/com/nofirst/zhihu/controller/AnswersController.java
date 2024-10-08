package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionsController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
public class AnswersController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public void store(@PathVariable Long questionId) {

    }
}
