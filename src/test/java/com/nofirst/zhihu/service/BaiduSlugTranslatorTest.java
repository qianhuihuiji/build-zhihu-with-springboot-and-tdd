package com.nofirst.zhihu.service;

import com.nofirst.zhihu.service.impl.BaiduTranslatorServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BaiduSlugTranslatorTest {

    @Test
    @Tag("online")
    void can_translate_chinese_to_english() {
        // given
        BaiduTranslatorServiceImpl translatorService = new BaiduTranslatorServiceImpl("20171222000107417", "");
        String text = "英语 英语";
        // when
        String translate = translatorService.translate(text);
        // then
        assertThat(translate).isEqualTo("english-english");
    }
}