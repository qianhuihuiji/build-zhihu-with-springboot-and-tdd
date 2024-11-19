package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions/{id}")
    public Question show(@PathVariable Long id) {
        return questionService.show(id);
    }
}
