package com.nofirst.zhihu.validator;

import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.model.dto.QuestionDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ValidCategoryValidator implements ConstraintValidator<ValidCategory, QuestionDto> {

    private CategoryMapper categoryMapper;

    @Override
    public boolean isValid(QuestionDto value, ConstraintValidatorContext context) {
        if (Objects.isNull(value.getCategoryId())) {
            return false;
        }

        Category category = categoryMapper.selectByPrimaryKey(value.getCategoryId());

        return Objects.nonNull(category);
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
