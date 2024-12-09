package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comment implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer commentedId;

    private String commentedType;

    private Date createdAt;

    private Date updatedAt;

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

    public Integer getCommentedId() {
        return commentedId;
    }

    public void setCommentedId(Integer commentedId) {
        this.commentedId = commentedId;
    }

    public String getCommentedType() {
        return commentedType;
    }

    public void setCommentedType(String commentedType) {
        this.commentedType = commentedType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        sb.append(", commentedId=").append(commentedId);
        sb.append(", commentedType=").append(commentedType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

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