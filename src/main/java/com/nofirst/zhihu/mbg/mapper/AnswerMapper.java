package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.AnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    long countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Answer row);

    int insertSelective(Answer row);

    List<Answer> selectByExampleWithBLOBs(AnswerExample example);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Answer row, @Param("example") AnswerExample example);

    int updateByExampleWithBLOBs(@Param("row") Answer row, @Param("example") AnswerExample example);

    int updateByExample(@Param("row") Answer row, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer row);

    int updateByPrimaryKeyWithBLOBs(Answer row);

    int updateByPrimaryKey(Answer row);
}