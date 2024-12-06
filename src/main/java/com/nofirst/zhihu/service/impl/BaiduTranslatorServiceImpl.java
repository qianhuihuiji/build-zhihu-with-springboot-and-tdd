package com.nofirst.zhihu.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.config.TranslatorConfig;
import com.nofirst.zhihu.service.TranslatorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Primary
@AllArgsConstructor
public class BaiduTranslatorServiceImpl implements TranslatorService {

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private TranslatorConfig translatorConfig;

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = this.buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        String appId = this.translatorConfig.getBaidu().getAppId();
        String appKey = this.translatorConfig.getBaidu().getAppKey();
        params.put("appid", appId);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        String src = appId + query + salt + appKey;
        params.put("sign", MD5.md5(src));
        return params;
    }

    @Override
    public String translate(String text) {
        String transResult = getTransResult(text, "auto", "en");
        JSONObject parse = JSONUtil.parseObj(transResult);
        JSONArray results = parse.getJSONArray("trans_result");
        if (Objects.nonNull(results)) {
            String dst = String.valueOf(((JSONObject) results.get(0)).get("dst"));

            // 将字符串转换成小写
            String lowerCase = dst.toLowerCase();

            // 将空格替换为中划线
            return lowerCase.replace(" ", "-");
        }

        return "";
    }
}
