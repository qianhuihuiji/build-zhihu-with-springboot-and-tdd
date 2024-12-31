package com.nofirst.zhihu.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * The type Notification vo.
 */
@Data
public class NotificationVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 通知类型
     */
    private String type;

    /**
     * 接收者的id，如：user的id
     */
    private Integer notifiableId;

    /**
     * 接收者的类名，如：User
     */
    private String notifiableType;

    private Date readAt;

    private Date createdAt;

    private Date updatedAt;
}