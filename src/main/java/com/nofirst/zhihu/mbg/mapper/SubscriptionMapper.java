package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.mbg.model.SubscriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubscriptionMapper {
    long countByExample(SubscriptionExample example);

    int deleteByExample(SubscriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Subscription row);

    int insertSelective(Subscription row);

    List<Subscription> selectByExample(SubscriptionExample example);

    Subscription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Subscription row, @Param("example") SubscriptionExample example);

    int updateByExample(@Param("row") Subscription row, @Param("example") SubscriptionExample example);

    int updateByPrimaryKeySelective(Subscription row);

    int updateByPrimaryKey(Subscription row);
}