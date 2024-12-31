package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Comment.
 */
public class Comment implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer commentedId;

    private String commentedType;

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
     * Gets commented id.
     *
     * @return the commented id
     */
    public Integer getCommentedId() {
        return commentedId;
    }

    /**
     * Sets commented id.
     *
     * @param commentedId the commented id
     */
    public void setCommentedId(Integer commentedId) {
        this.commentedId = commentedId;
    }

    /**
     * Gets commented type.
     *
     * @return the commented type
     */
    public String getCommentedType() {
        return commentedType;
    }

    /**
     * Sets commented type.
     *
     * @param commentedType the commented type
     */
    public void setCommentedType(String commentedType) {
        this.commentedType = commentedType;
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
        sb.append(", commentedId=").append(commentedId);
        sb.append(", commentedType=").append(commentedType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Mentioned users list.
     *
     * @return the list
     */
    public List<String> mentionedUsers() {
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