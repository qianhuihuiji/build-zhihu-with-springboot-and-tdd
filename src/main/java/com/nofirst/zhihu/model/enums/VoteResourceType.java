package com.nofirst.zhihu.model.enums;

/**
 * 投票的资源类型
 */
public enum VoteResourceType {

    ANSWER("answer", "回答"),

    QUESTION("question", "提问");

    private final String code;
    private final String description;

    VoteResourceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
