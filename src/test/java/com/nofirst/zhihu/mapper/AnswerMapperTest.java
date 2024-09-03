package com.nofirst.zhihu.mapper;

import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class AnswerMapperTest {

    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void testInsert() {
        List<Answer> answers = answerMapper.selectByQuestionId(1L);
        Assertions.assertEquals(1, answers.size());
    }

}
