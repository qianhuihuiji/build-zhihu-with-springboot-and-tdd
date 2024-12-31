package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.CommentExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Comment mapper.
 */
public interface CommentMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(CommentExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(CommentExample example);

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
    int insert(Comment row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Comment row);

    /**
     * Select by example with blo bs list.
     *
     * @param example the example
     * @return the list
     */
    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * Select by primary key comment.
     *
     * @param id the id
     * @return the comment
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Comment row, @Param("example") CommentExample example);

    /**
     * Update by example with blo bs int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleWithBLOBs(@Param("row") Comment row, @Param("example") CommentExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Comment row, @Param("example") CommentExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Comment row);

    /**
     * Update by primary key with blo bs int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(Comment row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Comment row);
}