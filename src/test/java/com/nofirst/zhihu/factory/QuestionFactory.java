package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.QuestionVo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class QuestionFactory {

    public static Question createPublishedQuestion() {
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);

        return Question.builder()
                .id(1L)
                .userId(1)
                .title("title")
                .content("content")
                .publishedAt(lastWeek)
                .build();
    }

    public static Question createUnpublishedQuestion() {

        return Question.builder()
                .id(1L)
                .userId(1)
                .title("title")
                .content("content")
                .publishedAt(null)
                .build();
    }

    public static QuestionVo createVO(Question question) {
        QuestionVo questionVo = new QuestionVo();
        questionVo.setId(question.getId());
        questionVo.setUserId(question.getUserId());
        questionVo.setBestAnswerId(question.getBestAnswerId());
        questionVo.setTitle(question.getTitle());
        questionVo.setContent(question.getContent());
        questionVo.setPublishedAt(question.getPublishedAt());

        return questionVo;
    }
}
