package com.nofirst.zhihu.listener;

import cn.hutool.json.JSONObject;
import com.nofirst.zhihu.event.PostCommentEvent;
import com.nofirst.zhihu.event.PublishQuestionEvent;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Comment;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Post comment event listener.
 */
@Component
@AllArgsConstructor
public class PostCommentEventListener {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;

    /**
     * Notify subscribed users.
     *
     * @param event the event
     */
    @EventListener
    public void notifySubscribedUsers(PostCommentEvent event) {
        Comment comment = event.getComment();

        String data = getData(comment);
        List<String> mentionedUsers = comment.mentionedUsers();
        for (String mentionedUser : mentionedUsers) {
            User user = userMapper.selectByUsername(mentionedUser);
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

    private String getData(Comment comment) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("content", comment.getContent());

        return jsonObject.toString();
    }
}
