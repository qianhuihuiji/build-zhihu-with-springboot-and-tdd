package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void can_insert_question() {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();

        // when:成功返回1
        int insert = questionMapper.insert(question);

        // then
        Question result = questionMapper.selectByPrimaryKey(1L);
        assertThat(insert).isEqualTo(1);
        assertThat(result).isNotNull();
    }
}
