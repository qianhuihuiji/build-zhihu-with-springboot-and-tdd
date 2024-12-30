package com.nofirst.zhihu.filter;

import com.nofirst.zhihu.dao.CategoryDao;
import com.nofirst.zhihu.mbg.mapper.UserMapper;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.mbg.model.CategoryExample;
import com.nofirst.zhihu.mbg.model.QuestionExample;
import com.nofirst.zhihu.mbg.model.User;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class QuestionFilter {

    private final UserMapper userMapper;
    private final CategoryDao categoryDao;

    public void apply(Map<String, Object> queryConditions, QuestionExample example, QuestionExample.Criteria criteria) {
        if (queryConditions.containsKey("by")) {
            by(criteria, String.valueOf(queryConditions.get("by")));
        }
        if (queryConditions.containsKey("popularity")) {
            popularity(example, (Integer) queryConditions.get("popularity"));
        }
        if (queryConditions.containsKey("unanswered")) {
            unanswered(criteria, (Integer) queryConditions.get("unanswered"));
        }
        if (queryConditions.containsKey("slug")) {
            slug(criteria, String.valueOf(queryConditions.get("slug")));
        }
    }

    private void by(QuestionExample.Criteria criteria, String username) {
        if (StringUtils.isNotBlank(username)) {
            User user = userMapper.selectByUsername(username);
            if (Objects.nonNull(user)) {
                criteria.andUserIdEqualTo(user.getId());
            }
        }
    }

    private void slug(QuestionExample.Criteria criteria, String slug) {
        if (StringUtils.isNotBlank(slug)) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andSlugLike(slug);
            Category category = categoryDao.selectBySlug(slug);
            if (Objects.nonNull(category)) {
                criteria.andCategoryIdEqualTo(category.getId());
            }
        }
    }

    private void popularity(QuestionExample example, Integer popularity) {
        if (Objects.nonNull(popularity) && popularity == 1) {
            example.setOrderByClause("answers_count desc");
        }
    }

    private void unanswered(QuestionExample.Criteria criteria, Integer unanswered) {
        if (Objects.nonNull(unanswered) && unanswered == 1) {
            criteria.andAnswersCountEqualTo(0);
        }
    }

}
