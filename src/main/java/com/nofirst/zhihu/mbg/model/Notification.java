package com.nofirst.zhihu.mbg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Notification.
 */
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
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets notifiable id.
     *
     * @return the notifiable id
     */
    public Integer getNotifiableId() {
        return notifiableId;
    }

    /**
     * Sets notifiable id.
     *
     * @param notifiableId the notifiable id
     */
    public void setNotifiableId(Integer notifiableId) {
        this.notifiableId = notifiableId;
    }

    /**
     * Gets notifiable type.
     *
     * @return the notifiable type
     */
    public String getNotifiableType() {
        return notifiableType;
    }

    /**
     * Sets notifiable type.
     *
     * @param notifiableType the notifiable type
     */
    public void setNotifiableType(String notifiableType) {
        this.notifiableType = notifiableType;
    }

    /**
     * Gets read at.
     *
     * @return the read at
     */
    public Date getReadAt() {
        return readAt;
    }

    /**
     * Sets read at.
     *
     * @param readAt the read at
     */
    public void setReadAt(Date readAt) {
        this.readAt = readAt;
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
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
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