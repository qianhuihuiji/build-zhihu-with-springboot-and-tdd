package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question implements Serializable {
    private Integer id;

    private Integer userId;

    private String title;

    private Date publishedAt;

    /**
     * 最佳答案
     *
     * @mbg.generated
     */
    private Integer bestAnswerId;

    /**
     * 分类编号
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     * 回答数量
     *
     * @mbg.generated
     */
    private Integer answersCount;

    private String slug;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Integer getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(Integer bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(Integer answersCount) {
        this.answersCount = answersCount;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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
        sb.append(", answersCount=").append(answersCount);
        sb.append(", slug=").append(slug);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public List<String> invitedUsers() {
        List<String> invitedUser = new ArrayList<>();
        Pattern p = Pattern.compile("(?<=@)\\S+");
        Matcher m = p.matcher(this.getContent());
        while (m.find()) {
            String username = m.group();
            invitedUser.add(username);
        }
        return invitedUser;
    }
}