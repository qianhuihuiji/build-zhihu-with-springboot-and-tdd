package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.NotificationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Notification mapper.
 */
public interface NotificationMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(NotificationExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(NotificationExample example);

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
    int insert(Notification row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Notification row);

    /**
     * Select by example with blo bs list.
     *
     * @param example the example
     * @return the list
     */
    List<Notification> selectByExampleWithBLOBs(NotificationExample example);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * Select by primary key notification.
     *
     * @param id the id
     * @return the notification
     */
    Notification selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Notification row, @Param("example") NotificationExample example);

    /**
     * Update by example with blo bs int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleWithBLOBs(@Param("row") Notification row, @Param("example") NotificationExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Notification row, @Param("example") NotificationExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Notification row);

    /**
     * Update by primary key with blo bs int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(Notification row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Notification row);
}