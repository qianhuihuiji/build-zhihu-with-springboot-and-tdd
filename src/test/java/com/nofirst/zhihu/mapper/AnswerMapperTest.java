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

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

    @Test
    public void testInsert2() {
        // given
        Answer answer = new Answer();
        answer.setId(2L);
        answer.setQuestionId(1L);
        answer.setUserId(1);
        Date now = new Date();
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);
        answer.setContent("just test");

        // when:成功返回1
        int insert = answerMapper.insert(answer);

        // then
        List<Answer> answers = answerMapper.selectByQuestionId(1L);
        assertThat(insert).isEqualTo(1);
        assertThat(answers.size()).isEqualTo(2);
    }

}
