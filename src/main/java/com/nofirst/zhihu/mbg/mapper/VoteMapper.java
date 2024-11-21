package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.mbg.model.VoteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMapper {
    long countByExample(VoteExample example);

    int deleteByExample(VoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Vote row);

    int insertSelective(Vote row);

    List<Vote> selectByExample(VoteExample example);

    Vote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Vote row, @Param("example") VoteExample example);

    int updateByExample(@Param("row") Vote row, @Param("example") VoteExample example);

    int updateByPrimaryKeySelective(Vote row);

    int updateByPrimaryKey(Vote row);

    Vote selectByVotedId(Long voteId, String resourceType, String actionType);

    int countByVotedId(Long voteId, String resourceType, String actionType);

    void deleteByVotedId(Long votedId, String resourceType, String actionType);
}