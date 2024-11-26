package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.NotificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotificationMapper {
    long countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notification row);

    int insertSelective(Notification row);

    List<Notification> selectByExampleWithBLOBs(NotificationExample example);

    List<Notification> selectByExample(NotificationExample example);

    Notification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Notification row, @Param("example") NotificationExample example);

    int updateByExampleWithBLOBs(@Param("row") Notification row, @Param("example") NotificationExample example);

    int updateByExample(@Param("row") Notification row, @Param("example") NotificationExample example);

    int updateByPrimaryKeySelective(Notification row);

    int updateByPrimaryKeyWithBLOBs(Notification row);

    int updateByPrimaryKey(Notification row);
}