package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private QuestionMapper questionMapper;

    @Override
    public Question show(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }
}
