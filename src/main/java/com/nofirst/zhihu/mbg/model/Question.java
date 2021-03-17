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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
