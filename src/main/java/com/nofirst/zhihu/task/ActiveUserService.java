package com.nofirst.zhihu.task;

import com.nofirst.zhihu.dao.AnswerDao;
import com.nofirst.zhihu.dao.QuestionDao;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.model.dto.UserCountDto;
import com.nofirst.zhihu.model.vo.UserVo;
import com.nofirst.zhihu.redis.JsonRedisTemplate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ActiveUserService {

    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private UserMapper userMapper;

    private JsonRedisTemplate jsonRedisTemplate;

    // 发布问题的得分权重
    public static Integer QUESTION_WEIGHT = 4;
    // 回答问题的得分权重
    public static Integer ANSWER_WEIGHT = 1;
    // 多少天内发表过内容
    public static Integer PASS_DAYS = 7;
    // 取出来多少用户
    public static Integer USER_NUMBER = 6;

    // 缓存相关配置
    public static String CACHE_KEY = "zhihu_active_users";
    public static Integer CACHE_EXPIRE_IN_SECONDS = 60 * 60;

    public List<UserVo> getActiveUsers() {
        // 先尝试获取缓存，获取不到则重新获取活跃用户，并存入缓存
        List<UserVo> userIds = (List<UserVo>) jsonRedisTemplate.opsForValue().get(CACHE_KEY);
        if (CollectionUtils.isEmpty(userIds)) {
            return calculateActiveUsers();
        }

        return userIds;
    }

    public void calculateAndCacheActiveUsers() {
        // 取得活跃用户列表
        List<UserVo> users = calculateActiveUsers();
        // 加以缓存
        cacheActiveUsers(users);
    }

    private List<UserVo> calculateActiveUsers() {
        LinkedHashMap<Integer, Integer> scoreMap = calculateQuestionScore();
        LinkedHashMap<Integer, Integer> finalScoreMap = calculateAnswerScore(scoreMap);

        List<Integer> userIds = finalScoreMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();

        int sizeToTake = Math.min(USER_NUMBER, userIds.size());

        List<Integer> activeUserIds = userIds.subList(0, sizeToTake);
        List<UserVo> activeUsers = new ArrayList<>(activeUserIds.size());
        for (Integer activeUserId : activeUserIds) {
            User user = userMapper.selectByPrimaryKey(activeUserId);
            if (Objects.nonNull(user)) {
                UserVo userVo = new UserVo();
                userVo.setId(user.getId());
                userVo.setName(user.getName());
                userVo.setPhone(user.getPhone());
                userVo.setEmail(user.getEmail());
                activeUsers.add(userVo);
            }
        }

        return activeUsers;
    }

    private LinkedHashMap<Integer, Integer> calculateQuestionScore() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        Date beginTime = DateUtils.addDays(new Date(), -PASS_DAYS);
        List<UserCountDto> userCountList = questionDao.count(beginTime);
        for (UserCountDto userCountDto : userCountList) {
            if (userCountDto.getCount() > 0) {
                map.put(userCountDto.getUserId(), userCountDto.getCount() * QUESTION_WEIGHT);
            }
        }

        return map;
    }

    private LinkedHashMap<Integer, Integer> calculateAnswerScore(LinkedHashMap<Integer, Integer> scoreMap) {
        Date beginTime = DateUtils.addDays(new Date(), -PASS_DAYS);
        List<UserCountDto> userCountList = answerDao.count(beginTime);
        for (UserCountDto userCountDto : userCountList) {
            if (userCountDto.getCount() > 0) {
                Integer baseScore = scoreMap.getOrDefault(userCountDto.getUserId(), 0);
                scoreMap.put(userCountDto.getUserId(), baseScore + userCountDto.getCount() * ANSWER_WEIGHT);
            }
        }

        return scoreMap;
    }

    private void cacheActiveUsers(List<UserVo> users) {
        // 将数据放入缓存中
        this.jsonRedisTemplate.opsForValue().set(CACHE_KEY, users, Duration.ofSeconds(CACHE_EXPIRE_IN_SECONDS));
    }
}
