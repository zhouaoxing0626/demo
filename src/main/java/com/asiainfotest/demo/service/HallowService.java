package com.asiainfotest.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.asiainfotest.demo.mapper.HallowMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HallowService {

    @Autowired
    private HallowMapper hallowMapper;

    public JSONObject modProdChannel(JSONObject jsonObject) {

        JSONObject result = new JSONObject();

        String prod = "";
        if (jsonObject.isEmpty()) {
            prod = hallowMapper.qryParamConfigByParanId("3").getParamValue();
            result.put("prod", prod);
        }
        result.put("respCode", "0000");
        result.put("respDesc", "成功");
        return result;
    }
}
