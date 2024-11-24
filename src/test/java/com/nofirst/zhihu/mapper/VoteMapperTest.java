package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VoteMapperTest {

    @Autowired
    private VoteMapper voteMapper;

    @BeforeAll
    public static void start() {
        mySQLContainer.start();
    }

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        voteMapper.deleteByExample(null);
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
    public void can_insert_vote() {
        // given
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        long beforeCount = voteMapper.countByExample(null);

        // when:成功返回1
        int insert = voteMapper.insert(vote);

        // then
        long afterCount = voteMapper.countByExample(null);
        assertThat(insert).isEqualTo(1);
        assertThat(afterCount - beforeCount).isEqualTo(1);
    }

    @Test
    public void can_select_by_voted_id() {
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        Vote result = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNotNull();
    }

    @Test
    public void can_delete_by_voted_id() {
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        Vote result = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNotNull();
        voteMapper.deleteByVotedId(vote.getVotedId(), vote.getResourceType(), vote.getActionType());
        result = voteMapper.selectByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNull();
    }

    @Test
    public void can_count_by_voted_id() {
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        int count = voteMapper.countByVotedId(1L, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(count).isEqualTo(1);
    }
}
