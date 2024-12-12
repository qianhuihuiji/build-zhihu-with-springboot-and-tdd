package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.model.vo.NotificationVo;

/**
 * UserNotificationService
 *
 * @author nofirst
 */
public interface UserNotificationService {

    PageInfo<NotificationVo> index(Integer userId, Integer pageIndex, Integer pageSize);

}
