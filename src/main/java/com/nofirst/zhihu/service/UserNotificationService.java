package com.nofirst.zhihu.service;

import com.github.pagehelper.PageInfo;
import com.nofirst.zhihu.model.vo.NotificationVo;

/**
 * UserNotificationService
 *
 * @author nofirst
 */
public interface UserNotificationService {

    /**
     * Index page info.
     *
     * @param userId    the user id
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the page info
     */
    PageInfo<NotificationVo> index(Integer userId, Integer pageIndex, Integer pageSize);

}
