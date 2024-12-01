package com.nofirst.zhihu.model.vo;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionVo {

    private Integer id;

    private Integer userId;

    private Integer bestAnswerId;

    private String title;

    private String content;

    private Date publishedAt;

    private Integer answersCount;

    PageInfo<Answer> answers;
}