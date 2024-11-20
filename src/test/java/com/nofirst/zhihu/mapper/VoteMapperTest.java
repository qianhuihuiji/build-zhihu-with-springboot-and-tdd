package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Vote;
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

    @Test
    public void can_insert_vote() {
        // given
        Vote vote = VoteFactory.createVoteUp();

        // when:成功返回1
        int insert = voteMapper.insert(vote);

        // then
        Vote result = voteMapper.selectByPrimaryKey(1L);
        assertThat(insert).isEqualTo(1);
        assertThat(result).isNotNull();
    }

    @Test
    public void can_select_by_voted_id() {
        Vote vote = VoteFactory.createVoteUp();
        voteMapper.insert(vote);
        List<Vote> votes = voteMapper.selectByVotedId(1L);
        assertThat(votes.size()).isEqualTo(1);
    }
}
