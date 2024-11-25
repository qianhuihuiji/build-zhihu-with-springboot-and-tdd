package com.nofirst.zhihu.listener;

import com.nofirst.zhihu.event.YouWereInvitedEvent;
import com.nofirst.zhihu.mbg.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoginEventMessageNoticeListener {

    @EventListener
    public void LoginEventMessageNoticeListener(YouWereInvitedEvent event) {
        User user = event.getUser();
        // 发送消息通知用户
        System.out.println("you are invited: " + user.getName());
    }
}
