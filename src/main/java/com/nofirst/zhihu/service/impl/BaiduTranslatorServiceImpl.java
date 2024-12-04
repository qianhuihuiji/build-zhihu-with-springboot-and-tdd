package com.nofirst.zhihu.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.service.TranslatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Primary
public class BaiduTranslatorServiceImpl implements TranslatorService {

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    @Value("${translate.baidu.app-id}")
    private String appid;
    @Value("${translate.baidu.app-key}")
    private String securityKey;

    public BaiduTranslatorServiceImpl(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = this.buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", this.appid);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        String src = this.appid + query + salt + this.securityKey;
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
