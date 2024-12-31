package com.nofirst.zhihu.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * The type Answer dto.
 */
@Data
public class AnswerDto {

    @NotBlank(message = "答案内容不能为空")
    private String content;
}
