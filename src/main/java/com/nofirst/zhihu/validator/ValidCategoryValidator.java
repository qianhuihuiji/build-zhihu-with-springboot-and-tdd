package com.nofirst.zhihu.validator;

import com.nofirst.zhihu.mbg.mapper.CategoryMapper;
import com.nofirst.zhihu.mbg.model.Category;
import com.nofirst.zhihu.model.dto.QuestionDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class ValidCategoryValidator implements ConstraintValidator<ValidCategory, QuestionDto> {

    private CategoryMapper categoryMapper;

    @Override
    public boolean isValid(QuestionDto value, ConstraintValidatorContext context) {
        Category category = categoryMapper.selectByPrimaryKey(value.getCategoryId());

        if (Objects.isNull(category)) {
            // 这里可以设置 ValidCategory 的 message，可以应对复杂的消息提醒
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("问题分类不存在")
//                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
