package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.dao.SubscriptionDao;
import com.nofirst.zhihu.event.PostAnswerEvent;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.mapper.ActivityMapper;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Activity;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.Subscription;
import com.nofirst.zhihu.mbg.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Post answer event listener.
 */
@Component
@AllArgsConstructor
public class PostAnswerEventListener {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;
    private final SubscriptionDao subscriptionDao;
    private final ActivityMapper activityMapper;

    /**
     * Notify subscribed users.
     *
     * @param event the event
     */
    @EventListener
    public void notifySubscribedUsers(PostAnswerEvent event) {
        Answer answer = event.getAnswer();
        List<Subscription> subscriptions = subscriptionDao.selectByQuestionId(answer.getQuestionId());
        String data = getData(answer);
        for (Subscription subscription : subscriptions) {
            if (!event.getUserId().equals(subscription.getUserId())) {
                User user = userMapper.selectByPrimaryKey(subscription.getUserId());
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
    }

    /**
     * Record activity.
     *
     * @param event the event
     */
    @EventListener
    public void recordActivity(PostAnswerEvent event) {
        Answer answer = event.getAnswer();
        Activity activity = new Activity();
        activity.setUserId(answer.getUserId());
        activity.setType("created_answer");
        activity.setSubjectId(answer.getId());
        activity.setSubjectType(answer.getClass().getSimpleName());
        Date now = new Date();
        activity.setCreatedAt(now);
        activity.setUpdatedAt(now);

        activityMapper.insert(activity);
    }

    private String getData(Answer answer) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("content", answer.getContent());

        return jsonObject.toString();
    }
}
