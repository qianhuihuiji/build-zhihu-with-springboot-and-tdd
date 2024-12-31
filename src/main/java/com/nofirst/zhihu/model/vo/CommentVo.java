package com.nofirst.zhihu.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * The type Comment vo.
 */
@Data
public class CommentVo {

    private Integer id;

    private Integer commentedId;

    private String content;

    private Date createTime;
}