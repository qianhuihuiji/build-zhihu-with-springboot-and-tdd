package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Question;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class QuestionFactory {

    public static Question createPushlishedQuestion() {
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);

        return Question.builder()
                .id(1L)
                .userId(1)
                .title("title")
                .content("content")
                .publishedAt(lastWeek)
                .build();
    }

    public static Question createUnpushlishedQuestion() {

        return Question.builder()
                .id(1L)
                .userId(1)
                .title("title")
                .content("content")
                .publishedAt(null)
                .build();
    }
}
