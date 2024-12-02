package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class PublishQuestionEventListener {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;

    @EventListener
    public void notifyInvitedUsers(PublishQuestionEvent event) {
        Question question = event.getQuestion();
        String data = getData(question);
        List<String> invitedUsers = question.invitedUsers();
        for (String invitedUser : invitedUsers) {
            User user = userMapper.selectByUsername(invitedUser);
            if (Objects.nonNull(user)) {
                Notification notification = new Notification();
                notification.setType(PublishQuestionEvent.class.getName());
                notification.setNotifiableId(user.getId());
                notification.setNotifiableType(User.class.getName());
                Date now = new Date();
                notification.setCreatedAt(now);
                notification.setUpdatedAt(now);
                notification.setData(data);

                notificationMapper.insert(notification);
            }
        }
    }

    private String getData(Question question) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("title", question.getTitle());
        jsonObject.set("content", question.getContent());

        return jsonObject.toString();
    }
}
