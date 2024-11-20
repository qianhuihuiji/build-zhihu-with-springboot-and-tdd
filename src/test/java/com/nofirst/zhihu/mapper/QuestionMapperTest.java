package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        questionMapper.deleteByExample(null);
    }

    @Test
    public void can_insert_question() {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();

        // when:成功返回1
        int insert = questionMapper.insert(question);

        // then
        // 这里的主键变成2了，因为数据脚本里面预置了一条数据，主键自增为2
        Question result = questionMapper.selectByPrimaryKey(2L);
        assertThat(insert).isEqualTo(1);
        assertThat(result).isNotNull();
    }
}
