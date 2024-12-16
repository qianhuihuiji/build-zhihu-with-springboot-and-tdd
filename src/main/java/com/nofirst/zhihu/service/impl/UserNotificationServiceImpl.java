package com.nofirst.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.mbg.mapper.NotificationMapper;
import com.nofirst.zhihu.mbg.model.Notification;
import com.nofirst.zhihu.mbg.model.NotificationExample;
import com.nofirst.zhihu.model.vo.NotificationVo;
import com.nofirst.zhihu.service.UserNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserNotificationServiceImpl implements UserNotificationService {

    private final NotificationMapper notificationMapper;

    @Override
    public PageInfo<NotificationVo> index(Integer userId, Integer pageIndex, Integer pageSize) {
        markUnreadNotificationsAsRead();

        PageHelper.startPage(pageIndex, pageSize);
        NotificationExample example = new NotificationExample();
        NotificationExample.Criteria criteria = example.createCriteria();
        criteria.andNotifiableIdEqualTo(userId);
        example.setOrderByClause("created_at desc");
        List<Notification> notifications = notificationMapper.selectByExample(example);
        // 如果不使用 mapper 返回的对象直接构造分页对象，total会被错误赋值成当前页的数据的数量，而不是总数
        PageInfo<Notification> notificationPageInfo = new PageInfo<>(notifications);
        List<NotificationVo> result = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationVo notificationVo = new NotificationVo();
            notificationVo.setId(notification.getId());
            notificationVo.setType(notification.getType());
            notificationVo.setNotifiableId(notification.getNotifiableId());
            notificationVo.setNotifiableType(notification.getNotifiableType());
            notificationVo.setReadAt(notification.getReadAt());
            notificationVo.setCreatedAt(notification.getCreatedAt());
            notificationVo.setUpdatedAt(notification.getUpdatedAt());

            result.add(notificationVo);
        }
        PageInfo<NotificationVo> pageResult = new PageInfo<>();
        pageResult.setTotal(notificationPageInfo.getTotal());
        pageResult.setPageNum(notificationPageInfo.getPageNum());
        pageResult.setPageSize(notificationPageInfo.getPageSize());
        pageResult.setList(result);
        return pageResult;
    }

    private void markUnreadNotificationsAsRead() {
        Notification row = new Notification();
        row.setReadAt(new Date());

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReadAtIsNull();
        notificationMapper.updateByExampleSelective(row, example);
    }
}
