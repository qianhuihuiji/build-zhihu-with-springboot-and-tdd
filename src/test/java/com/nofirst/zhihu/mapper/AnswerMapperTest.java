package com.nofirst.zhihu.mapper;

import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.dao.AnswerDao;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class AnswerMapperTest extends BaseContainerTest {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private AnswerDao answerDao;

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        answerMapper.deleteByExample(null);
    }

    @Test
    public void can_select_by_user_id() {
        // given
        Answer answer = new Answer();
        answer.setId(2);
        answer.setQuestionId(1);
        answer.setUserId(1);
        Date now = new Date();
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);
        answer.setContent("just test");
        answerMapper.insert(answer);
        // when
        List<Answer> answers = answerDao.selectByUserId(1L);
        // then
        assertThat(!answers.isEmpty()).isTrue();
    }

    @Test
    public void can_select_by_question_id() {
        // given
        Answer answer = new Answer();
        answer.setId(2);
        answer.setQuestionId(1);
        answer.setUserId(1);
        Date now = new Date();
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);
        answer.setContent("just test");

        // when:成功返回1
        int insert = answerMapper.insert(answer);

        // then
        List<Answer> answers = answerDao.selectByQuestionId(1);
        assertThat(insert).isEqualTo(1);
        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    public void it_knows_if_it_is_the_best() {
        // given
        Question question = QuestionFactory.createPublishedQuestion();
        Answer answer = AnswerFactory.createAnswer(question.getId());
        question.setBestAnswerId(answer.getId());

        // then
        assertThat(answer.isBest(question)).isEqualTo(true);
    }

}
