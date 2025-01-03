package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.service.TranslatorService;
import org.springframework.stereotype.Service;

/**
 * The type Fake translator service.
 */
@Service
public class FakeTranslatorServiceImpl implements TranslatorService {

    @Override
    public String translate(String text) {
        return "english-english";
    }
}
