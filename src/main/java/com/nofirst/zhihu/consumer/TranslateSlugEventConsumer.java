package com.nofirst.zhihu.consumer;

import com.nofirst.zhihu.event.TranslateSlugEvent;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import com.nofirst.zhihu.service.TranslatorService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TranslateSlugEventConsumer {

    private TranslatorService translatorService;
    private QuestionMapper questionMapper;

    @KafkaListener(topics = "${translate.topic}", groupId = "demo")
    public void listen(TranslateSlugEvent translateSlugEvent) {
        System.out.println("Received: " + translateSlugEvent);
        Question question = translateSlugEvent.getQuestion();
        String translate = translatorService.translate(question.getTitle());
        question.setSlug(translate);

        questionMapper.updateByPrimaryKey(question);
    }
}
