package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteType;
import com.nofirst.zhihu.model.enums.VoteUpType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class VoteMapperTest {

    @Autowired
    private VoteMapper voteMapper;

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        voteMapper.deleteByExample(null);
    }

    @Test
    public void can_insert_vote() {
        // given
        Vote vote = VoteFactory.createVoteUp(VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());

        // when:成功返回1
        int insert = voteMapper.insert(vote);

        // then
        Vote result = voteMapper.selectByPrimaryKey(1L);
        assertThat(insert).isEqualTo(1);
        assertThat(result).isNotNull();
    }

    @Test
    public void can_select_by_voted_id() {
        Vote vote = VoteFactory.createVoteUp(VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        List<Vote> votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(1);
    }

    @Test
    public void can_delete_by_voted_id() {
        Vote vote = VoteFactory.createVoteUp(VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        List<Vote> votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(1);
        voteMapper.deleteByVotedId(vote.getVotedId(), vote.getVotedType(), vote.getType());
        votes = voteMapper.selectByVotedId(1L, VoteType.ANSWER.getCode(), VoteUpType.VOTE_UP.getCode());
        assertThat(votes.size()).isEqualTo(0);
    }
}
