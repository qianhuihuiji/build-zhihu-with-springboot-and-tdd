package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.QuestionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Question mapper.
 */
public interface QuestionMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(QuestionExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(QuestionExample example);

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
    int insert(Question row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Question row);

    /**
     * Select by example with blo bs list.
     *
     * @param example the example
     * @return the list
     */
    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Question> selectByExample(QuestionExample example);

    /**
     * Select by primary key question.
     *
     * @param id the id
     * @return the question
     */
    Question selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Question row, @Param("example") QuestionExample example);

    /**
     * Update by example with blo bs int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleWithBLOBs(@Param("row") Question row, @Param("example") QuestionExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Question row, @Param("example") QuestionExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Question row);

    /**
     * Update by primary key with blo bs int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(Question row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Question row);
}