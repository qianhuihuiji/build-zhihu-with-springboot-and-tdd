package com.nofirst.zhihu.mapper;


import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.dao.VoteDao;
import com.nofirst.zhihu.factory.VoteFactory;
import com.nofirst.zhihu.mbg.mapper.VoteMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.model.enums.VoteActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class VoteMapperTest extends BaseContainerTest {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteDao voteDao;

    @BeforeEach
    public void setUp() {
        // 每次运行测试前，先把数据库清空
        voteMapper.deleteByExample(null);
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
        Vote result = voteDao.selectByVotedId(1, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNotNull();
    }

    @Test
    public void can_delete_by_voted_id() {
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        Vote result = voteDao.selectByVotedId(1, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNotNull();
        voteDao.deleteByVotedId(vote.getVotedId(), vote.getResourceType(), vote.getActionType());
        result = voteDao.selectByVotedId(1, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(result).isNull();
    }

    @Test
    public void can_count_by_voted_id() {
        Vote vote = VoteFactory.createVote(Answer.class, VoteActionType.VOTE_UP.getCode());
        voteMapper.insert(vote);
        int count = voteDao.countByVotedId(1, Answer.class.getSimpleName(), VoteActionType.VOTE_UP.getCode());
        assertThat(count).isEqualTo(1);
    }
}
