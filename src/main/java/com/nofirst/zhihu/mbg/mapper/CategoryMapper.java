package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.mbg.model.CategoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Category mapper.
 */
public interface CategoryMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(CategoryExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(CategoryExample example);

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
    int insert(Category row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Category row);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Category> selectByExample(CategoryExample example);

    /**
     * Select by primary key category.
     *
     * @param id the id
     * @return the category
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Category row, @Param("example") CategoryExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Category row, @Param("example") CategoryExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Category row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Category row);
}