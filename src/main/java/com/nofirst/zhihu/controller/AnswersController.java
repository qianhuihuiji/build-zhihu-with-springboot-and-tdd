package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * QuestionsController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
public class AnswersController {

    @PostMapping("/questions/{questionId}/answers")
    public void store(@PathVariable Long questionId) {

    }
}
