package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TranslateSlugEvent {

    private Question question;

    public TranslateSlugEvent(Question question) {
        this.question = question;
    }
}
