package com.nofirst.zhihu;

import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class QuestionFilter {

    private final UserMapper userMapper;

    private List<String> filters = Arrays.asList("by", "popularity");

    public void apply(Map<String, String> queryConditions, QuestionExample example) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<String, String> entry : queryConditions.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            Method method = ReflectionUtils.findMethod(this.getClass(), k);
            if (Objects.nonNull(method)) {
                method.invoke(this, example, v);
            }
        }

    }

    private void by(QuestionExample example, String value) {
        if (StringUtils.isNotBlank(value)) {
            User user = userMapper.selectByUsername(value);
            if (Objects.nonNull(user)) {
                example.createCriteria().andUserIdEqualTo(user.getId());
            }
        }
    }
}
