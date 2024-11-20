package com.nofirst.zhihu.model.enums;

/**
 * 投票的类型
 */
public enum VoteUpType {

    VOTE_UP("vote_up", "赞同"),

    VOTE_DOWN("vote_down", "反对");

    private final String code;
    private final String description;

    VoteUpType(String code, String description) {
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
