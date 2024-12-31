package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Question.
 */
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

    private Date createdAt;

    private Date updatedAt;

    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets published at.
     *
     * @return the published at
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets published at.
     *
     * @param publishedAt the published at
     */
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets best answer id.
     *
     * @return the best answer id
     */
    public Integer getBestAnswerId() {
        return bestAnswerId;
    }

    /**
     * Sets best answer id.
     *
     * @param bestAnswerId the best answer id
     */
    public void setBestAnswerId(Integer bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets category id.
     *
     * @param categoryId the category id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets answers count.
     *
     * @return the answers count
     */
    public Integer getAnswersCount() {
        return answersCount;
    }

    /**
     * Sets answers count.
     *
     * @param answersCount the answers count
     */
    public void setAnswersCount(Integer answersCount) {
        this.answersCount = answersCount;
    }

    /**
     * Gets slug.
     *
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     *
     * @param slug the slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
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
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Invited users list.
     *
     * @return the list
     */
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