package com.nofirst.zhihu.producer;

import com.nofirst.zhihu.event.TranslateSlugEvent;
import com.nofirst.zhihu.mbg.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Question question) {
        TranslateSlugEvent translateSlugEvent = new TranslateSlugEvent(question);
        kafkaTemplate.send(topic, translateSlugEvent);
        System.out.println("Sent message: " + question);
    }
}
