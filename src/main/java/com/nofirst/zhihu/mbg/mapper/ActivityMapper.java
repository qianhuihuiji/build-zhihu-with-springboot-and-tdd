package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Activity;
import com.nofirst.zhihu.mbg.model.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity row);

    int insertSelective(Activity row);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByExample(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity row);

    int updateByPrimaryKey(Activity row);
}