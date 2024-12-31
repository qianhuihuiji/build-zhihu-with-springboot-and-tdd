package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Vote;
import com.nofirst.zhihu.mbg.model.VoteExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Vote mapper.
 */
public interface VoteMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(VoteExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(VoteExample example);

    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param row the row
     * @return the int
     */
    int insert(Vote row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Vote row);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Vote> selectByExample(VoteExample example);

    /**
     * Select by primary key vote.
     *
     * @param id the id
     * @return the vote
     */
    Vote selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Vote row, @Param("example") VoteExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Vote row, @Param("example") VoteExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Vote row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Vote row);
}