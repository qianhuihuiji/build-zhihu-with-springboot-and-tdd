package com.nofirst.zhihu.mbg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {
    private Long id;

    /**
     * 创建用户id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 发布时间
     *
     * @mbg.generated
     */
    private Date publishedAt;

    /**
     * 最佳答案
     *
     * @mbg.generated
     */
    private Long bestAnswerId;

    /**
     * 分类编号
     *
     * @mbg.generated
     */
    private Short categoryId;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String content;

    private static final long serialVersionUID = 1L;


    public Question(Long id, Integer userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", publishedAt=").append(publishedAt);
        sb.append(", bestAnswerId=").append(bestAnswerId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}