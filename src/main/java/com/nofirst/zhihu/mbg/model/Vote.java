package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Vote implements Serializable {
    private Long id;

    private Integer userId;

    private Long votedId;

    private String votedType;

    private Date createdAt;

    private Date updatedAt;

    private String type;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getVotedId() {
        return votedId;
    }

    public void setVotedId(Long votedId) {
        this.votedId = votedId;
    }

    public String getVotedType() {
        return votedType;
    }

    public void setVotedType(String votedType) {
        this.votedType = votedType;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", votedId=").append(votedId);
        sb.append(", votedType=").append(votedType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}