package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.AnswerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Answer mapper.
 */
public interface AnswerMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(AnswerExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(AnswerExample example);

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
    int insert(Answer row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Answer row);

    /**
     * Select by example with blo bs list.
     *
     * @param example the example
     * @return the list
     */
    List<Answer> selectByExampleWithBLOBs(AnswerExample example);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Answer> selectByExample(AnswerExample example);

    /**
     * Select by primary key answer.
     *
     * @param id the id
     * @return the answer
     */
    Answer selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Answer row, @Param("example") AnswerExample example);

    /**
     * Update by example with blo bs int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleWithBLOBs(@Param("row") Answer row, @Param("example") AnswerExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Answer row, @Param("example") AnswerExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Answer row);

    /**
     * Update by primary key with blo bs int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(Answer row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Answer row);
}