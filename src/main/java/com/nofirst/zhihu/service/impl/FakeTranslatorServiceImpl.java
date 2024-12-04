package com.nofirst.zhihu.service.impl;

import com.nofirst.zhihu.service.TranslatorService;
import org.springframework.stereotype.Service;

@Service
public class FakeTranslatorServiceImpl implements TranslatorService {

    @Override
    public String translate(String text) {
        return "english-english";
    }
}
