package com.nofirst.zhihu.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityVo {

    private Long id;

    private Long questionId;

    private Integer userId;

    private Date createdAt;

    private Date updatedAt;

    private String content;
}