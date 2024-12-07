package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment row);

    int insertSelective(Comment row);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Comment row, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("row") Comment row, @Param("example") CommentExample example);

    int updateByExample(@Param("row") Comment row, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment row);

    int updateByPrimaryKeyWithBLOBs(Comment row);

    int updateByPrimaryKey(Comment row);
}