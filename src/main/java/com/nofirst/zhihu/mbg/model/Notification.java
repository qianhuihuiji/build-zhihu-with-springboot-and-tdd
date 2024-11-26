package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 通知类型
     *
     * @mbg.generated
     */
    private String type;

    /**
     * 接收者的id，如：user的id
     *
     * @mbg.generated
     */
    private Integer notifiableId;

    /**
     * 接收者的类名，如：User
     *
     * @mbg.generated
     */
    private String notifiableType;

    private Date readAt;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 通知内容
     *
     * @mbg.generated
     */
    private String data;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNotifiableId() {
        return notifiableId;
    }

    public void setNotifiableId(Integer notifiableId) {
        this.notifiableId = notifiableId;
    }

    public String getNotifiableType() {
        return notifiableType;
    }

    public void setNotifiableType(String notifiableType) {
        this.notifiableType = notifiableType;
    }

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", notifiableId=").append(notifiableId);
        sb.append(", notifiableType=").append(notifiableType);
        sb.append(", readAt=").append(readAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", data=").append(data);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}