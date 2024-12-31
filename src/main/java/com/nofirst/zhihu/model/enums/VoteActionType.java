package com.nofirst.zhihu.model.enums;

/**
 * 投票的动作类型
 */
public enum VoteActionType {

    /**
     * Vote up vote action type.
     */
    VOTE_UP("vote_up", "赞同"),

    /**
     * Vote down vote action type.
     */
    VOTE_DOWN("vote_down", "反对");

    private final String code;
    private final String description;

    VoteActionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
