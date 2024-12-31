package com.nofirst.zhihu.event;

import com.nofirst.zhihu.mbg.model.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Translate slug event.
 */
@Data
@NoArgsConstructor
public class TranslateSlugEvent {

    private Question question;

    /**
     * Instantiates a new Translate slug event.
     *
     * @param question the question
     */
    public TranslateSlugEvent(Question question) {
        this.question = question;
    }
}
