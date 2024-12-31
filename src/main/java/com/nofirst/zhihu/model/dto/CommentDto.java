package com.nofirst.zhihu.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * The type Comment dto.
 */
@Data
public class CommentDto {

    @NotBlank(message = "评论内容不能为空")
    private String content;
}
