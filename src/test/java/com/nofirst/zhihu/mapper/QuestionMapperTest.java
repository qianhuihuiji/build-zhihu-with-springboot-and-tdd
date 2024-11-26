package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionDao questionDao;

    @BeforeAll
    public static void start() {
        mySQLContainer.start();
    }

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        questionMapper.deleteByExample(null);
    }

    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("zhihu")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }

    @Test
    public void can_insert_question() {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        int totalBefore = questionMapper.countByExample(null);

        // when:成功返回1
        questionMapper.insert(question);

        // then
        int totalAfter = questionMapper.countByExample(null);
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
        questionDao.markAsBestAnswer(question.getId(), 1L);
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
