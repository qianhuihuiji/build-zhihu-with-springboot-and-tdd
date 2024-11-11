package com.nofirst.zhihu.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnswerDto {

    private Integer userId;

    @NotBlank(message = "答案内容不能为空")
    private String content;
}
