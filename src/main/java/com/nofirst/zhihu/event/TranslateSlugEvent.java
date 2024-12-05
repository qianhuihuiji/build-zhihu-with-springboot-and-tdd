package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import lombok.Getter;

public class TranslateSlugEvent {

    @Getter
    private final Question question;
    
    public TranslateSlugEvent(Question question) {
        this.question = question;
    }
}
