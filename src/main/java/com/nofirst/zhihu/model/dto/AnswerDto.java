package com.nofirst.zhihu.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AnswerDto {

    @NotBlank(message = "答案内容不能为空")
    private String content;
}
