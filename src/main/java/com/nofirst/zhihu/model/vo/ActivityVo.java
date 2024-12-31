package com.nofirst.zhihu.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * The type Activity vo.
 */
@Data
public class ActivityVo {

    private Long id;

    private Long questionId;

    private Integer userId;

    private Date createdAt;

    private Date updatedAt;

    private String content;
}