package com.nofirst.zhihu;

import com.nofirst.zhihu.dao.CategoryDao;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.mbg.model.CategoryExample;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
@AllArgsConstructor
public class QuestionFilter {

    private final UserMapper userMapper;
    private final CategoryDao categoryDao;

    private Set<String> filters;

    static {
        new HashSet<String>() {{
            add("by");
            add("popularity");
            add("slug");
        }};
    }

    public void apply(Map<String, Object> queryConditions, QuestionExample example) {
        for (Map.Entry<String, Object> entry : queryConditions.entrySet()) {
            String k = entry.getKey();
            if (!filters.contains(k)) {
                // not supported
                continue;
            }
            Object v = entry.getValue();
            try {
                Method method = ReflectionUtils.findMethod(this.getClass(), k);
                if (Objects.nonNull(method)) {
                    method.invoke(this, example, v);
                }
            } catch (Exception e) {
                // do something
            }
        }
    }

    private void by(QuestionExample example, String username) {
        if (StringUtils.isNotBlank(username)) {
            User user = userMapper.selectByUsername(username);
            if (Objects.nonNull(user)) {
                example.createCriteria().andUserIdEqualTo(user.getId());
            }
        }
    }

    private void slug(QuestionExample example, String slug) {
        if (StringUtils.isNotBlank(slug)) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andSlugLike(slug);
            Category category = categoryDao.selectBySlug(slug);
            if (Objects.nonNull(category)) {
                example.createCriteria().andCategoryIdEqualTo(category.getId());
            }
        }
    }

    private void popularity(QuestionExample example, Integer popularity) {
        if (Objects.nonNull(popularity) && popularity == 1) {
            example.setOrderByClause("answers_count desc");
        }
    }

    private void unanswered(QuestionExample example, Integer unanswered) {
        if (Objects.nonNull(unanswered) && unanswered == 1) {
            example.createCriteria().andAnswersCountEquals(0);
        }
    }

}
