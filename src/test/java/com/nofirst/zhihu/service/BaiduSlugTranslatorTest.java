package com.nofirst.zhihu.service;

import com.nofirst.zhihu.config.BaiduTranslatorConfig;
import com.nofirst.zhihu.config.TranslatorConfig;
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
        TranslatorConfig translatorConfig = new TranslatorConfig();
        BaiduTranslatorConfig baiduTranslatorConfig = new BaiduTranslatorConfig();
        baiduTranslatorConfig.setAppId("20171222000107417");
        baiduTranslatorConfig.setAppKey("xxxxxx");
        translatorConfig.setBaidu(baiduTranslatorConfig);

        BaiduTranslatorServiceImpl translatorService = new BaiduTranslatorServiceImpl(translatorConfig);
        String text = "英语 英语";
        // when
        String translate = translatorService.translate(text);
        // then
        assertThat(translate).isEqualTo("english-english");
    }
}