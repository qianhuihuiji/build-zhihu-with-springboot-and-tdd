package com.nofirst.zhihu.model.vo;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.model.Answer;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionVo {

    private Long id;

    private Integer userId;

    private Long bestAnswerId;

    private String title;

    private String content;

    private Date publishedAt;

    PageInfo<Answer> answers;
}