package com.nofirst.zhihu.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Translator config.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "translate")
public class TranslatorConfig {

    private String topic;

    private BaiduTranslatorConfig baidu;
}


