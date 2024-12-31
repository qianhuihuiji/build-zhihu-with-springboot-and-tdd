package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Activity;
import com.nofirst.zhihu.mbg.model.ActivityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Activity mapper.
 */
public interface ActivityMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(ActivityExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(ActivityExample example);

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
    int insert(Activity row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Activity row);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Activity> selectByExample(ActivityExample example);

    /**
     * Select by primary key activity.
     *
     * @param id the id
     * @return the activity
     */
    Activity selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Activity row, @Param("example") ActivityExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Activity row, @Param("example") ActivityExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Activity row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Activity row);
}