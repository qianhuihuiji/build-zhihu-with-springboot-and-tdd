package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.mapper.ActivityMapper;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Activity;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.mbg.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Publish question event listener.
 */
@Component
@AllArgsConstructor
public class PublishQuestionEventListener {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;
    private final ActivityMapper activityMapper;

    /**
     * Notify invited users.
     *
     * @param event the event
     */
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

    /**
     * Record activity.
     *
     * @param event the event
     */
    @EventListener
    public void recordActivity(PublishQuestionEvent event) {
        Question question = event.getQuestion();
        Activity activity = new Activity();
        activity.setUserId(question.getUserId());
        activity.setType("published_question");
        activity.setSubjectId(question.getId());
        activity.setSubjectType(question.getClass().getSimpleName());
        Date now = new Date();
        activity.setCreatedAt(now);
        activity.setUpdatedAt(now);

        activityMapper.insert(activity);
    }

    private String getData(Question question) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("title", question.getTitle());
        jsonObject.set("content", question.getContent());

        return jsonObject.toString();
    }
}
