package com.nofirst.zhihu.mbg.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Question
 *
 * @author nofirst
 * @date 2020-08-24 22:37
 */
@Data
@AllArgsConstructor
public class Question {
    private Long id;
    private String title;
    private String content;
}
