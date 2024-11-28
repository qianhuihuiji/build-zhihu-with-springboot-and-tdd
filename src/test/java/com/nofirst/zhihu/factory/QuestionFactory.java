package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.model.vo.QuestionVo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionFactory {

    public static Question createPublishedQuestion() {
        Date lastWeek = DateUtils.addWeeks(new Date(), -1);

        return Question.builder()
                .userId(1)
                .title("this is a published question")
                .content("published content")
                .publishedAt(lastWeek)
                .categoryId((short) 1)
                .build();
    }

    public static List<Question> createPublishedQuestionBatch(Integer times) {
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < times; i++) {
            questions.add(createPublishedQuestion());
        }

        return questions;
    }

    public static Question createUnpublishedQuestion() {

        return Question.builder()
                .userId(1)
                .title("this is a unpublished question")
                .content("unpublished content")
                .publishedAt(null)
                .categoryId((short) 1)
                .build();
    }

    public static List<Question> createUnpublishedQuestionBatch(Integer times) {
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < times; i++) {
            questions.add(createUnpublishedQuestion());
        }

        return questions;
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
