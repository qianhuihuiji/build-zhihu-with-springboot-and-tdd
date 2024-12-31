package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.mbg.model.SubscriptionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Subscription mapper.
 */
public interface SubscriptionMapper {
    /**
     * Count by example long.
     *
     * @param example the example
     * @return the long
     */
    long countByExample(SubscriptionExample example);

    /**
     * Delete by example int.
     *
     * @param example the example
     * @return the int
     */
    int deleteByExample(SubscriptionExample example);

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
    int insert(Subscription row);

    /**
     * Insert selective int.
     *
     * @param row the row
     * @return the int
     */
    int insertSelective(Subscription row);

    /**
     * Select by example list.
     *
     * @param example the example
     * @return the list
     */
    List<Subscription> selectByExample(SubscriptionExample example);

    /**
     * Select by primary key subscription.
     *
     * @param id the id
     * @return the subscription
     */
    Subscription selectByPrimaryKey(Integer id);

    /**
     * Update by example selective int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExampleSelective(@Param("row") Subscription row, @Param("example") SubscriptionExample example);

    /**
     * Update by example int.
     *
     * @param row     the row
     * @param example the example
     * @return the int
     */
    int updateByExample(@Param("row") Subscription row, @Param("example") SubscriptionExample example);

    /**
     * Update by primary key selective int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKeySelective(Subscription row);

    /**
     * Update by primary key int.
     *
     * @param row the row
     * @return the int
     */
    int updateByPrimaryKey(Subscription row);
}