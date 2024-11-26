package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.event.YouWereInvitedEvent;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class YouWereInvitedEventListener {

    private final NotificationMapper notificationMapper;

    @EventListener
    public void youWereInvitedEventListener(YouWereInvitedEvent event) {
        User user = event.getUser();
        Notification notification = new Notification();
        notification.setType(YouWereInvitedEvent.class.getName());
        notification.setNotifiableId(user.getId());
        notification.setNotifiableType(User.class.getName());
        Date now = new Date();
        notification.setCreatedAt(now);
        notification.setUpdatedAt(now);
        notification.setData(getData(event.getQuestion()));

        notificationMapper.insert(notification);
    }

    private String getData(Question question) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("title", question.getTitle());
        jsonObject.set("content", question.getContent());

        return jsonObject.toString();
    }
}
