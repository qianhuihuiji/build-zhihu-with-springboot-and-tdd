package com.nofirst.zhihu.mbg.mapper;

import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.AnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    int countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExampleWithBLOBs(AnswerExample example);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExampleWithBLOBs(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKeyWithBLOBs(Answer record);

    int updateByPrimaryKey(Answer record);

    List<Answer> selectByQuestionId(Long questionId);
}