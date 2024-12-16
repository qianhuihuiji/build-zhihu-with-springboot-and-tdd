package com.nofirst.zhihu.task;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@AllArgsConstructor
public class CalculateActiveUserTask {

    private ActiveUserService activeUserService;

    @Scheduled(cron = "0 0 1 * * *")
    public void run() {
        activeUserService.calculateAndCacheActiveUsers();
    }

}
