package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.mbg.model.VoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteMapper {
    long countByExample(VoteExample example);

    int deleteByExample(VoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vote row);

    int insertSelective(Vote row);

    List<Vote> selectByExample(VoteExample example);

    Vote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Vote row, @Param("example") VoteExample example);

    int updateByExample(@Param("row") Vote row, @Param("example") VoteExample example);

    int updateByPrimaryKeySelective(Vote row);

    int updateByPrimaryKey(Vote row);
}