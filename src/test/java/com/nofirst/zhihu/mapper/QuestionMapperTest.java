package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class QuestionMapperTest extends BaseContainerTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionDao questionDao;

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        questionMapper.deleteByExample(null);
    }

    @Test
    public void can_insert_question() {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        long totalBefore = questionMapper.countByExample(null);

        // when:成功返回1
        questionMapper.insert(question);

        // then
        long totalAfter = questionMapper.countByExample(null);
        // 新增了一条数据
        assertThat(totalAfter - totalBefore).isEqualTo(1);
    }

    @Test
    public void can_mark_an_answer_as_best() {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        questionMapper.insert(question);

        Question result = questionMapper.selectByPrimaryKey(question.getId());
        assertThat(result.getBestAnswerId()).isNull();
        // when
        questionDao.markAsBestAnswer(question.getId(), 1);
        // then
        result = questionMapper.selectByPrimaryKey(question.getId());
        assertThat(result.getBestAnswerId()).isNotNull();
    }

    @Test
    public void can_publish_question() {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        questionMapper.insert(question);

        Question result = questionMapper.selectByPrimaryKey(question.getId());
        assertThat(result.getPublishedAt()).isNull();
        // when
        questionDao.publish(question.getId(), new Date());
        // then
        result = questionMapper.selectByPrimaryKey(question.getId());
        assertThat(result.getPublishedAt()).isNotNull();
    }
}
