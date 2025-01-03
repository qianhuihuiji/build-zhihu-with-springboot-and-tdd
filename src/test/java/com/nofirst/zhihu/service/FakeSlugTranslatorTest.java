package com.nofirst.zhihu.service;

import com.nofirst.zhihu.service.impl.FakeTranslatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FakeSlugTranslatorTest {

    @InjectMocks
    private FakeTranslatorServiceImpl translatorService;

    @Test
    void can_translate_chinese_to_english() {
        //
        String text = "英语 英语";
        // when
        String translate = translatorService.translate(text);
        // then
        assertThat(translate).isEqualTo("english-english");
    }
}