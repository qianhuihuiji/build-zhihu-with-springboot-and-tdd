package com.nofirst.zhihu.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * The type Answer vo.
 */
@Data
public class AnswerVo {

    private Long questionId;

    private Integer userId;

    private Date createdAt;

    private Date updatedAt;

    private String content;
}