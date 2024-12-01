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

        Question question = new Question();
        question.setUserId(1);
        question.setTitle("this is a published question");
        question.setPublishedAt(lastWeek);
        question.setCategoryId((short) 1);
        question.setAnswersCount(0);
        question.setContent("published content");

        return question;
    }

    public static List<Question> createPublishedQuestionBatch(Integer times) {
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < times; i++) {
            questions.add(createPublishedQuestion());
        }

        return questions;
    }

    public static Question createUnpublishedQuestion() {
        Question question = new Question();
        question.setUserId(1);
        question.setTitle("this is a unpublished question");
        question.setPublishedAt(null);
        question.setCategoryId((short) 1);
        question.setContent("unpublished content");
        question.setAnswersCount(0);

        return question;
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
