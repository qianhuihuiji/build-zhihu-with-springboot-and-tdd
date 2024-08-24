package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionsController
 *
 * @author nofirst
 * @date 2020-08-24 22:24
 */
@RestController
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/questions/{id}")
    public Question show(@PathVariable Long id) {
        return questionsService.show(id);
    }
}
