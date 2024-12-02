package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.dao.SubscriptionDao;
import com.nofirst.zhihu.event.PostAnswerEvent;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
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

@Component
@AllArgsConstructor
public class PostAnswerEventListener {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;
    private final SubscriptionDao subscriptionDao;

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

    private String getData(Answer answer) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("content", answer.getContent());

        return jsonObject.toString();
    }
}
